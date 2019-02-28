using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Drawing.Imaging;
using System.IO;
using System.Runtime.InteropServices;
using System.Drawing.Drawing2D;

namespace Graphics_2
{
    public partial class Form1 : Form
    {
        OpenFileDialog ofd = new OpenFileDialog();

        SaveFileDialog sfd = new SaveFileDialog();

        bool curColor1, curColor2 = false;
        Color CurrentColor1, CurrentColor2;
        bool isClick = false;
        int x, y, lx, ly = 0;
        Item CurrentItem = 0;

        public enum Item
        {
            Ball, Rectangle, Circle, RoundRectangle, Pencil, Eraser, Line, Sprey, Palitra, Brush1, Pour, Brush2, Brush3, Brush4
        }

        public Form1()
        {
            InitializeComponent();
            сохранитьToolStripMenuItem.Enabled = false;
        }

        private void загрузитьToolStripMenuItem_Click(object sender, EventArgs e)
        {

            ofd.Filter = "All image files(*.*)| *.*";
            if (ofd.ShowDialog() == DialogResult.OK)
            {
                pictureBox1.Image = Image.FromFile(ofd.FileName);
                сохранитьToolStripMenuItem.Enabled = true;
            }
        }

        private void сохранитьToolStripMenuItem_Click(object sender, EventArgs e)
        {

            Bitmap bmp = new Bitmap(flowLayoutPanel1.Width, flowLayoutPanel1.Height);
            Graphics g = Graphics.FromImage(bmp);
            Rectangle rect = flowLayoutPanel1.RectangleToScreen(flowLayoutPanel1.ClientRectangle);
            g.CopyFromScreen(rect.Location, Point.Empty, flowLayoutPanel1.Size);
            g.Dispose();
            SaveFileDialog s = new SaveFileDialog();
            s.Filter = "JPG files(*.jpg)|*.jpg | GIF files(*.gif)|*.gif | PNG files(*.png)|*.png | BMP files(*.bmp)|*.bmp";
            if (s.ShowDialog() == System.Windows.Forms.DialogResult.OK)
            {
                if (File.Exists(s.FileName))
                {
                    File.Delete(s.FileName);
                }
                if (s.FileName.Contains(".jpg"))
                {
                    bmp.Save(s.FileName, ImageFormat.Jpeg);
                }
                else if (s.FileName.Contains(".png"))
                {
                    bmp.Save(s.FileName, ImageFormat.Png);
                }
                else if (s.FileName.Contains(".bmp"))
                {
                    bmp.Save(s.FileName, ImageFormat.Bmp);
                }
                else if (s.FileName.Contains(".gif"))
                {
                    bmp.Save(s.FileName, ImageFormat.Gif);
                }
            }
        }

        private void выйтиToolStripMenuItem_Click(object sender, EventArgs e)
        {
            if (MessageBox.Show("Вы хотите выйти ?", "Выход", MessageBoxButtons.YesNo, MessageBoxIcon.Information) == DialogResult.Yes)
            {
                Application.Exit();
            }
        }

        private void label2_Click(object sender, EventArgs e)
        {

        }

        private void button23_Click(object sender, EventArgs e)
        {

        }

        private void button14_Click(object sender, EventArgs e)
        {
            label5.Text = "Используеммый инструмент : " + "Палитра №1";
            DialogResult dia = colorDialog1.ShowDialog();
            if (dia == DialogResult.OK)
            {
                CurrentColor1 = colorDialog1.Color;
                сохранитьToolStripMenuItem.Enabled = true;
            }
        }

        private void button1_Click(object sender, EventArgs e)
        {
            trackBar1.Maximum = 15;
            trackBar1.Minimum = 1;
            CurrentItem = Item.Line;
            label5.Text = "Используеммый инструмент : " + "Линия";
            button11.Visible = false;
            button14.Image = Properties.Resources.rainbow;
        }

        private void button2_Click(object sender, EventArgs e)
        {
            button11.Visible = true;
            button14.Image = Properties.Resources.one;
            trackBar1.Maximum = 50;
            trackBar1.Minimum = 1;
            CurrentItem = Item.Rectangle;
            label5.Text = "Используеммый инструмент : " + "Прямоугольник";
        }

        private void button5_Click(object sender, EventArgs e)
        {
            button11.Visible = true;
            button14.Image = Properties.Resources.one;
            trackBar1.Maximum = 50;
            trackBar1.Minimum = 1;
            CurrentItem = Item.RoundRectangle;
            label5.Text = "Используеммый инструмент : " + "Прямоугольник с закруглёнными углами";
        }

        private void pictureBox1_MouseMove(object sender, MouseEventArgs e)
        {
            label1.Text = "X = " + e.X + ", Y = " + e.Y + "  (пкс)";
            if (isClick)
            {
                lx = e.X;
                ly = e.Y;

                Graphics graf = pictureBox1.CreateGraphics();
                var rand = new Random();
                

                switch (CurrentItem)
                {
                    case Item.Sprey:

                        if (trackBar1.Value <= 11)
                        {
                            for (int i = 0; i < 7; i++)
                            {
                                graf.FillEllipse(new SolidBrush(CurrentColor1)/* new Pen(CurrentColor, trackBar1.Value)*/, e.X + rand.Next(10), e.Y + rand.Next(10), 2, 2);
                            }
                        }
                        if (trackBar1.Value <= 25 && trackBar1.Value >= 12)
                        {
                            for (int i = 0; i < 37; i++)
                            {
                                graf.FillEllipse(new SolidBrush(CurrentColor1)/*new Pen(CurrentColor, trackBar1.Value)*/, e.X + rand.Next(30), e.Y + rand.Next(30), 2, 2);
                            }
                        }
                        if (trackBar1.Value <= 40 && trackBar1.Value >= 26)
                        {
                            for (int i = 0; i < 100; i++)
                            {
                                graf.FillEllipse(new SolidBrush(CurrentColor1)/*new Pen(CurrentColor, trackBar1.Value)*/, e.X + rand.Next(50), e.Y + rand.Next(50), 2, 2);
                            }
                        }

                        break;
                    case Item.Pencil:
                        graf.FillRectangle(new SolidBrush(CurrentColor1), e.X, e.Y, trackBar1.Value, trackBar1.Value);
                        break;
                    case Item.Eraser:
                        graf.FillEllipse(new SolidBrush(Color.White), e.X, e.Y, trackBar1.Value, trackBar1.Value);
                        break;
                    case Item.Brush1:
                        graf.FillEllipse(new SolidBrush(CurrentColor1), e.X, e.Y, trackBar1.Value, trackBar1.Value);
                        break;
                    case Item.Brush2:
                        graf.DrawLine(new Pen(CurrentColor1, trackBar1.Value),x, y, e.X, e.Y);
                        x = lx;
                        y = ly;
                        break;
                    case Item.Brush3:
                        graf.DrawEllipse(new Pen(CurrentColor1), e.X, e.Y, trackBar1.Value, trackBar1.Value);
                        break;
                    case Item.Brush4:
                        graf.DrawRectangle(new Pen(CurrentColor1), e.X, e.Y, trackBar1.Value, trackBar1.Value);
                        break;
                    case Item.Rectangle:
                        graf.FillRectangle(new SolidBrush(CurrentColor1), Math.Min(x, e.X) - trackBar1.Value, Math.Min(y, e.Y) - trackBar1.Value, Math.Max(x, lx) - Math.Min(x, lx) + trackBar1.Value * 2, Math.Max(y, ly) - Math.Min(y, ly) + trackBar1.Value * 2);
                        graf.FillRectangle(new SolidBrush(CurrentColor2), Math.Min(x, e.X), Math.Min(y, e.Y), Math.Max(x, lx) - Math.Min(x, lx), Math.Max(y, ly) - Math.Min(y, ly));
                        //graf.DrawRectangle(new Pen(CurrentColor1, trackBar1.Value), Math.Min(x, e.X), Math.Min(y, e.Y), Math.Max(x, lx) - Math.Min(x, lx), Math.Max(y, ly) - Math.Min(y, ly));
                        break;
                }
                graf.Dispose();
            }
        }

        private void button13_Click(object sender, EventArgs e)
        {
            trackBar1.Maximum = 80;
            trackBar1.Minimum = 5;
            CurrentItem = Item.Eraser;
            label5.Text = "Используеммый инструмент : " + "Стёрка";
            button11.Visible = false;
            button14.Image = Properties.Resources.rainbow;
        }

        private void button4_Click(object sender, EventArgs e)
        {
            trackBar1.Maximum = 40;
            trackBar1.Minimum = 1;
            CurrentItem = Item.Sprey;
            label5.Text = "Используеммый инструмент : " + "Спрей";
            button11.Visible = false;
            button14.Image = Properties.Resources.rainbow;
        }

        private void button3_Click(object sender, EventArgs e)
        {
            CurrentItem = Item.Pour;
            label5.Text = "Используеммый инструмент : " + "Заливка";
            button11.Visible = false;
            button14.Image = Properties.Resources.rainbow;
        }

        private void button8_Click(object sender, EventArgs e)
        {
            trackBar1.Maximum = 7;
            trackBar1.Minimum = 1;
            CurrentItem = Item.Pencil;
            label5.Text = "Используеммый инструмент : " + "Карандаш";
            button11.Visible = false;
            button14.Image = Properties.Resources.rainbow;
        }

        private void button7_Click(object sender, EventArgs e)
        {
            trackBar1.Maximum = 80;
            trackBar1.Minimum = 5;
            CurrentItem = Item.Brush1;
            label5.Text = "Используеммый инструмент : " + "Кисть мягкая";
            button11.Visible = false;
            button14.Image = Properties.Resources.rainbow;
        }

        private void button15_Click(object sender, EventArgs e)
        {
            if (MessageBox.Show("Вы хотите выйти ?", "Выход", MessageBoxButtons.YesNo, MessageBoxIcon.Information) == DialogResult.Yes)
            {
                Application.Exit();
            }
        }

        private void button17_Click(object sender, EventArgs e)
        {
            trackBar1.Maximum = 80;
            trackBar1.Minimum = 5;
            CurrentItem = Item.Brush2;
            label5.Text = "Используеммый инструмент : " + "Кисть из жесткого волоса";
            button11.Visible = false;
            button14.Image = Properties.Resources.rainbow;
        }

        private void button22_Click(object sender, EventArgs e)
        {
            trackBar1.Maximum = 80;
            trackBar1.Minimum = 5;
            CurrentItem = Item.Brush3;
            label5.Text = "Используеммый инструмент : " + "Кольчужная кисть";
            button11.Visible = false;
            button14.Image = Properties.Resources.rainbow;
        }

        private void button18_Click(object sender, EventArgs e)
        {
            trackBar1.Maximum = 80;
            trackBar1.Minimum = 5;
            CurrentItem = Item.Brush4;
            label5.Text = "Используеммый инструмент : " + "Сетчетая кисть";
            button11.Visible = false;
            button14.Image = Properties.Resources.rainbow;
        }

        private void button16_Click(object sender, EventArgs e)
        {
            flowLayoutPanel1.Refresh();
            pictureBox1.Refresh();
            pictureBox1.Image = null;
            //сохранитьToolStripMenuItem.Enabled = false;
        }

        private void Form1_Load(object sender, EventArgs e)
        {

        }

        private void новыйToolStripMenuItem_Click(object sender, EventArgs e)
        {
            flowLayoutPanel1.Refresh();
            pictureBox1.Refresh();
            pictureBox1.Image = null;
            //сохранитьToolStripMenuItem.Enabled = false;
        }

        private void trackBar1_ValueChanged(object sender, EventArgs e)
        {
            label3.Text = trackBar1.Value.ToString();
        }

        private void button11_Click(object sender, EventArgs e)
        {
            label5.Text = "Используеммый инструмент : " + "Палитра №2";
            DialogResult dia = colorDialog1.ShowDialog();
            if (dia == DialogResult.OK)
            {
                CurrentColor2 = colorDialog1.Color;
                сохранитьToolStripMenuItem.Enabled = true;
            }
        }

        private void button6_Click(object sender, EventArgs e)
        {
            button11.Visible = true;
            button14.Image = Properties.Resources.one;
            trackBar1.Maximum = 50;
            trackBar1.Minimum = 1;
            CurrentItem = Item.Circle;
            label5.Text = "Используеммый инструмент : " + "Круг";
        }

        private void pictureBox1_MouseDown(object sender, MouseEventArgs e)
        {
            isClick = true;
            x = e.X;
            y = e.Y;
        }

        private void pictureBox1_MouseUp(object sender, MouseEventArgs e)
        {
            isClick = false;
            if (CurrentItem == Item.Line)
            {
                Graphics graf = pictureBox1.CreateGraphics();
                graf.DrawLine(new Pen(CurrentColor1, trackBar1.Value), new Point(x, y), new Point(lx, ly));
                graf.Dispose();
            }
            //else if (CurrentItem == Item.Rectangle)
            //{
            //    Graphics graf = pictureBox1.CreateGraphics();
            //    // graf.FillRectangle(new SolidBrush(CurrentColor2), Math.Min(x, e.X), Math.Min(y, e.Y), Math.Max(x, lx) - Math.Min(x, lx), Math.Max(y, ly) - Math.Min(y, ly));
            //    graf.DrawRectangle(new Pen(CurrentColor1, trackBar1.Value), Math.Min(x, e.X), Math.Min(y, e.Y), Math.Max(x, lx) - Math.Min(x, lx), Math.Max(y, ly) - Math.Min(y, ly));
            //    graf.Dispose();
            //}
            else if (CurrentItem == Item.Circle)
            {
                Graphics graf = pictureBox1.CreateGraphics();
                graf.FillEllipse(new SolidBrush(CurrentColor2), Math.Min(x, e.X), Math.Min(y, e.Y), Math.Max(x, lx) - Math.Min(x, lx), Math.Max(y, ly) - Math.Min(y, ly));
                graf.DrawEllipse(new Pen(CurrentColor1, trackBar1.Value), Math.Min(x, e.X), Math.Min(y, e.Y), Math.Max(x, lx) - Math.Min(x, lx), Math.Max(y, ly) - Math.Min(y, ly));
                graf.Dispose();
            }
            else if (CurrentItem == Item.RoundRectangle)
            {
                Graphics graf = pictureBox1.CreateGraphics();
                Pen pen = new Pen(CurrentColor1, trackBar1.Value);
                pen.LineJoin = LineJoin.Round;                             //задаем скошенные углы
                pen.MiterLimit = 5;                                        //задаем ограничение толщины скошенных углов
                graf.FillRectangle(new SolidBrush(CurrentColor2), Math.Min(x, e.X), Math.Min(y, e.Y), Math.Max(x, lx) - Math.Min(x, lx), Math.Max(y, ly) - Math.Min(y, ly));
                graf.DrawRectangle(pen, new Rectangle(Math.Min(x, e.X), Math.Min(y, e.Y), Math.Max(x, lx) - Math.Min(x, lx), Math.Max(y, ly) - Math.Min(y, ly)));
                graf.Dispose();
            }
            else if (CurrentItem == Item.Sprey)
            {
                Graphics g = pictureBox1.CreateGraphics();
                Random rand = new Random();

                if (trackBar1.Value <= 11)
                {
                    for (int i = 0; i < 7; i++)
                    {
                        g.FillEllipse(new SolidBrush(CurrentColor1)/* new Pen(CurrentColor, trackBar1.Value)*/, e.X + rand.Next(10), e.Y + rand.Next(10), 2, 2);
                    }
                }
                if (trackBar1.Value <= 25 && trackBar1.Value >= 12)
                {
                    for (int i = 0; i < 37; i++)
                    {
                        g.FillEllipse(new SolidBrush(CurrentColor1)/*new Pen(CurrentColor, trackBar1.Value)*/, e.X + rand.Next(30), e.Y + rand.Next(30), 2, 2);
                    }
                }
                if (trackBar1.Value <= 40 && trackBar1.Value >= 26)
                {
                    for (int i = 0; i < 100; i++)
                    {
                        g.FillEllipse(new SolidBrush(CurrentColor1)/*new Pen(CurrentColor, trackBar1.Value)*/, e.X + rand.Next(50), e.Y + rand.Next(50), 2, 2);
                    }
                }
            }
        }
    }
}
            

