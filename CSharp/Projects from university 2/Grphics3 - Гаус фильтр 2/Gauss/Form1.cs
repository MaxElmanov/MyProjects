using System;
using System.Drawing;
using System.Drawing.Imaging;
using System.Windows.Forms;

public class Form1 : Form
{
    [STAThread] static void Main() { Application.Run(new Form1()); }
    Bitmap b0, b2;
    const Int32 N = 3;          //N должно быть нечетным целым числом N >= 9, но N < b0.Width && N < b0.Height
    Byte[,] R0, G0, B0;
    String s1, s2;

    public Form1()
    {
        MenuItem miRead = new MenuItem("&Read", new EventHandler(MenuFileRead));
        MenuItem miExit = new MenuItem("&Exit", new EventHandler(MenuFileExit));
        MenuItem miFile = new MenuItem("&File", new MenuItem[] { miRead, miExit });
        Menu = new System.Windows.Forms.MainMenu(new MenuItem[] { miFile });
        Text = "Fast and Simple Gauss Filters";
        SetStyle(ControlStyles.ResizeRedraw, true);
        //try
        //{
        //    b0 = new Bitmap(typeof(Form1), "gauss_fast.Butterfly.jpg");
        //    byte_arrays_and_b1_image();
        //    convolution_with_a_gauss_filter(N);
        //    //border_painting();
        //}
        //catch { }
        Width = 1930;
        Height =1100;
    }

    //private void InitializeComponent()
    //{
    //        this.SuspendLayout();
    //        // 
    //        // Form1
    //        // 
    //        this.ClientSize = new System.Drawing.Size(284, 261);
    //        this.Name = "Графика 3";
    //        this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
    //        this.ResumeLayout(false);

    //}

    void MenuFileRead(object obj, EventArgs ea)
    {
        OpenFileDialog dlg = new OpenFileDialog();
        dlg.Filter = "All image files(*.*)| *.*";          //"JPG files(*.jpg)|*.JPG | GIF files(*.gif)|*.gif | PNG files(*.png)|*.png | BMP files(*.bmp)|*.bmp";
        if (dlg.ShowDialog() != DialogResult.OK)
        {
            return;
        }
        else
        {
            b0 = (Bitmap)Image.FromFile(dlg.FileName);
            byte_arrays_and_b1_image();
            convolution_with_a_gauss_filter(N);
            Invalidate();
        }
    }

    private void InitializeComponent()
    {
            this.SuspendLayout();
            // 
            // Form1
            // 
            this.ClientSize = new System.Drawing.Size(284, 261);
            this.Name = "Form1";
            this.Load += new System.EventHandler(this.Form1_Load);
            this.ResumeLayout(false);

    }

    private void Form1_Load(object sender, EventArgs e)
    {

    }

    void MenuFileExit(object obj, EventArgs ea)
    { Application.Exit(); }

    protected override void OnPaint(PaintEventArgs e)
    {
        Graphics g = e.Graphics;
        g.Clear(BackColor);
        try
        {
            g.DrawImage(b0, 0, 10, b0.Width, b0.Height);
            if (b0.Width < 100)
            {
                g.DrawImage(b2, 200, 10);
            }
            else
            {
                g.DrawImage(b2, b0.Width + 10, 10);
            }
            g.DrawString(s1, new Font("Arial", 16), new SolidBrush(Color.Red),  10, b0.Height + 20);
            if (b0.Width < 200)
            {
                g.DrawString(s2, new Font("Arial", 16), new SolidBrush(Color.Red), 200, b0.Height + 20);
            }
            else
            {
                g.DrawString(s2, new Font("Arial", 16), new SolidBrush(Color.Red), b0.Width + 10, b0.Height + 20);
            }
        }
        catch { }
    }

    private void byte_arrays_and_b1_image()
    {
        R0 = new Byte[b0.Height, b0.Width];
        G0 = new Byte[b0.Height, b0.Width];
        B0 = new Byte[b0.Height, b0.Width];

        //if (b1 != null) b1.Dispose();
        if (b2 != null) b2.Dispose();

        //b1 = new Bitmap(b0);
        b2 = new Bitmap(b0);
        for (int y = 0; y < b0.Height; y++)
            for (int x = 0; x < b0.Width; x++)
            {
                Color c = b2.GetPixel(x, y);
                R0[y, x] = (Byte)c.R;
                G0[y, x] = (Byte)c.G;
                B0[y, x] = (Byte)c.B;

                //b1.SetPixel(x, y, Color.Black);
                b2.SetPixel(x, y, Color.Black);
            }
    }

    private void convolution_with_a_gauss_filter(int N)
    {
        Int32 Nh = N / 2;
        Int32 x, xx, xxx, y, yy, yyy;

        float[,] kernel = new float[N, N];  // квадратное ядро Гаусса
        float sum_kernel = 0.0f;
        float sumR, sumG, sumB, weight, Rf, Gf, Bf;
        Cursor.Current = Cursors.WaitCursor;

        // Построение подходящего двумерного гауссова ядра колоколообразной формы:
        // Угловые элементы ядра: Nh * sqrt (2) вдали от центра
        // и, следовательно, получить наименьшие значения.
        // Мы корректируем параметр а е-функции у = е - (а * х2), поэтому,
        // что всегда (при любом размере ядра, кроме 3х3) эти углы получают не менее 1% веса.
        double a = 1.0f;
        if (N > 3) a = -2 * Nh * Nh / Math.Log(0.01);

        //заполнить ядро элементами в зависимости от их расстояния и a.
        for (y = 0; y < N; y++)
            for (x = 0; x < N; x++)
            {
                double dist = Math.Sqrt((x - Nh) * (x - Nh) + (y - Nh) * (y - Nh));
                kernel[y, x] = (float)(Math.Exp(-dist * dist / a));
                sum_kernel += kernel[y, x];
            }
        //свертка
        for (y = Nh; y < b0.Height - Nh; y++)     //==================
        {
            for (x = Nh; x < b0.Width - Nh; x++)  //===============
            { 
                sumR = sumG = sumB = 0.0f;
                for (yy = -Nh; yy <= Nh; yy++)    //=============
                {
                    yyy = y + yy;
                    for (xx = -Nh; xx <= Nh; xx++)//========
                    {
                        weight = kernel[yy + Nh, xx + Nh];
                        xxx = x + xx;
                        sumR += weight * R0[yyy, xxx];
                        sumG += weight * G0[yyy, xxx];
                        sumB += weight * B0[yyy, xxx];
                    } //====== end for (int xx... ================
                } //======== end for (int yy... ==================
                Rf = sumR / sum_kernel; Gf = sumG / sum_kernel; Bf = sumB / sum_kernel;
                b2.SetPixel(x, y, Color.FromArgb(Convert.ToInt32(Rf),
                                                   Convert.ToInt32(Gf),
                                                   Convert.ToInt32(Bf)));
            } //============ end for (int x... =====================
        } //============== end for (int y... =======================
        s1 = "Original picture\r\n" +
            "Image:  " + b2.Width.ToString() + " x " + b2.Height.ToString() + "\r\n";
        s2 = "Simple quadratic Gauss filter\r\n" +
             "Image:  " + b0.Width.ToString() + " x " + b0.Height.ToString() + "\r\n" +
             "Filter: " + N.ToString() + " x " + N.ToString() + "\r\n";
        Cursor.Current = Cursors.Arrow;
    }
}