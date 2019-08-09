using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace task_1
{
    public partial class Form2 : Form
    {

        public Form2()
        {
            InitializeComponent();
        }

        private void Form2_Load(object sender, EventArgs e)
        {
            toolStripButton2.Enabled = false;
            сохранитьToolStripMenuItem.Enabled = false;
        }

        private void выйтиToolStripMenuItem_Click(object sender, EventArgs e)
        {
            Application.Exit();
        }

        OpenFileDialog ofd = new OpenFileDialog();

        private void загрузитьToolStripMenuItem_Click(object sender, EventArgs e)
        {
            toolStripButton2.Enabled = true;
            сохранитьToolStripMenuItem.Enabled = true;

            toolStripButton1.Image = Properties.Resources._2_open1;
            ofd.Filter = "Image Files(*.BMP)| *.bmp";
           if( ofd.ShowDialog()== DialogResult.OK)
            {
                textBox1.Text = ofd.SafeFileName;
                textBox2.Text = ofd.FileName;
                pictureBox1.Image = Image.FromFile(ofd.FileName);
                pictureBox1.SizeMode = PictureBoxSizeMode.StretchImage;
                pictureBox1.BorderStyle = BorderStyle.Fixed3D;
                toolStripButton1.Image = Properties.Resources._2_open;
            }
        }

        
        SaveFileDialog sfd = new SaveFileDialog();

        private void сохранитьToolStripMenuItem_Click(object sender, EventArgs e)
        {
            toolStripButton2.Enabled = false;
            сохранитьToolStripMenuItem.Enabled = false;

            toolStripButton2.Image = Properties.Resources._4_save_off;
            if (pictureBox1.Image != null)
            {
                sfd.Filter = "Image Files(*.PNG)| *.png";
                sfd.OverwritePrompt = true;
                sfd.Title = "Save image as...";
                sfd.CheckPathExists = true;
                sfd.ShowHelp = true;
                if (sfd.ShowDialog() == DialogResult.OK)
                {
                    try
                    {
                        pictureBox1.Image.Save(sfd.FileName);
                        toolStripButton2.Image = Properties.Resources._3_save;
                    }
                    catch
                    {
                        MessageBox.Show("The image is not saved", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                    }
                }
            }
        }

        private void оПрограммеToolStripMenuItem_Click(object sender, EventArgs e)
        {
            Form3 f3 = new Form3();
            f3.Show();

        }

        private void button1_Click(object sender, EventArgs e)
        {
            pictureBox1.Width = (int)(pictureBox1.Width * 1.2);
            pictureBox1.Height = (int)(pictureBox1.Height * 1.2);

        }

        private void button2_Click(object sender, EventArgs e)
        {
            pictureBox1.Width = (int)(pictureBox1.Width / 1.2);
            pictureBox1.Height = (int)(pictureBox1.Height / 1.2);
        }

        private void cправкаToolStripMenuItem_Click(object sender, EventArgs e)
        {
            Form4 f4 = new Form4();
            f4.Show();
        }
    }
}
