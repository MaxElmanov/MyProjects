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
    public partial class Form3 : Form
    {
        public Form3()
        {
            InitializeComponent();
            richTextBox1.BackColor = Color.LightGreen;

        }

        private void richTextBox1_TextChanged(object sender, EventArgs e)
        {
            richTextBox1.BackColor = Color.Aqua;

        }

        private void button1_Click(object sender, EventArgs e)
        {
            richTextBox1.Clear();
        }

        private void button2_Click(object sender, EventArgs e)
        {
            richTextBox1.Copy();
        }

        private void button4_Click(object sender, EventArgs e)
        {
           richTextBox1.Cut();
        }

        private void button3_Click(object sender, EventArgs e)
        {
            richTextBox1.Paste();
        }
    }
}
