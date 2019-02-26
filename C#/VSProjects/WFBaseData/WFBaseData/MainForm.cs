using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace WFBaseData
{
    public partial class MainForm : Form
    {
        SqlConnection sc;

        public MainForm()
        {
            InitializeComponent();
        }

        private async void MainForm_Load(object sender, EventArgs e)
        {
            string connectionStr = @"Data Source=(LocalDB)\v11.0;AttachDbFilename=D:\VSProjects\WFBaseData\WFBaseData\Database.mdf;Integrated Security=True";

            sc = new SqlConnection(connectionStr);

            await sc.OpenAsync();

            SqlDataReader sqlReader = null;

            SqlCommand command = new SqlCommand("SELECT * FROM [Store]", sc);

            try
            {
                sqlReader = await command.ExecuteReaderAsync();

                while(await sqlReader.ReadAsync())
                {
                    listBox1.Items.Add(Convert.ToString(sqlReader["Id"] + "\t" + sqlReader["Name"] + "\t" + sqlReader["Price"]));
                }
            }
            catch(Exception exe){
                MessageBox.Show(exe.Message, exe.Source.ToString(), MessageBoxButtons.OK, MessageBoxIcon.Asterisk);
            }
            finally
            {
                if (sqlReader != null)
                sqlReader.Close();
            }
        }

        private void exitToolStripMenuItem_Click(object sender, EventArgs e)
        {
            Application.Exit();
        }

        private void MainForm_FormClosing(object sender, FormClosingEventArgs e)
        {
            if (sc != null && sc.State != System.Data.ConnectionState.Closed)
                sc.Close();
        }

        private async void btn_add_Click(object sender, EventArgs e)
        {
            var productName = txt_name.Text;
            var price = txt_price.Text;

            if(productName == String.Empty || price == String.Empty) 
            {
                MessageBox.Show("One of fileds is empty", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
            else
            {
                SqlCommand sqlCommand = new SqlCommand("INSERT INTO [Store] (Name, Price) VALUES(@Name, @Price)", sc);

                sqlCommand.Parameters.AddWithValue("Name", productName);
                sqlCommand.Parameters.AddWithValue("Price", price);

                await sqlCommand.ExecuteNonQueryAsync();
            }

            txt_name.Clear();
            txt_price.Clear();
        }

        private async void updateToolStripMenuItem_Click(object sender, EventArgs e)
        {
            listBox1.Items.Clear();

            SqlDataReader sqlreader = null;

            SqlCommand sqlCommand = new SqlCommand("SELECT * FROM [Store]", sc);

            try
            {
                sqlreader = await sqlCommand.ExecuteReaderAsync();

                while(await sqlreader.ReadAsync()) 
                {
                    listBox1.Items.Add(Convert.ToString(sqlreader["Id"] + "\t" + sqlreader["Name"] + "\t" + sqlreader["Price"]));
                }
            }
            catch(Exception ex) 
            {
                MessageBox.Show(ex.Message, ex.Source.ToString(), MessageBoxButtons.OK, MessageBoxIcon.Asterisk);
            }
            finally
            {
                if(sqlreader != null) 
                {
                    sqlreader.Close();
                }
            }
        }

        private async void button2_Click(object sender, EventArgs e)
        {
            var id = txt_id.Text;
            var productName = txt_UpdateProductName.Text;
            var price = txt_UpdatePrice.Text;

            if( String.IsNullOrEmpty(id) || String.IsNullOrEmpty(productName) || String.IsNullOrEmpty(price)) 
            {
                MessageBox.Show("One of fileds is empty", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
            else
            {
                SqlCommand command = new SqlCommand("UPDATE [Store] SET Name=@Name, Price=@Price WHERE Id=@id", sc);

                command.Parameters.AddWithValue("id", id);
                command.Parameters.AddWithValue("Name", productName);
                command.Parameters.AddWithValue("Price", price);

                await command.ExecuteNonQueryAsync();
            }

            txt_id.Clear();
            txt_UpdateProductName.Clear();
            txt_UpdatePrice.Clear();
        }

        private async void button3_Click(object sender, EventArgs e)
        {
            if(string.IsNullOrEmpty(txt_DeleteId.Text)) 
            {
                MessageBox.Show("Filed is empty", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
            else
            {
                SqlCommand command = new SqlCommand("DELETE FROM [Store] WHERE Id=@id", sc);

                command.Parameters.AddWithValue("id", txt_DeleteId.Text);

                await command.ExecuteNonQueryAsync();
            }

            txt_DeleteId.Clear();
        }
    }
}
