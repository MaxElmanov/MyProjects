using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
//using System.Drawing;
//using System.Drawing.Drawing2D;


namespace task_1
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();

            timer1.Start();
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            //GraphicsPath path = new GraphicsPath();
            //path.AddEllipse(0, 0, this.Width-25, this.Height-50);
            //Region rgn = new Region(path);
            //this.Region = rgn;
        }

        private void timer1_Tick(object sender, EventArgs e)
        {
           
            if (progressBar1.Value == progressBar1.Maximum)
            {
                timer1.Stop();
                Form2 f2 = new Form2();
                f2.Show();
                this.Hide();
            }else
            progressBar1.Value += 4;
    }
        }
}
