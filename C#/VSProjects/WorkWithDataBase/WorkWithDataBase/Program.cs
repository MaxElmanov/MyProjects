using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Data.SqlClient;
using System.Data;
using System.Threading.Tasks;

namespace WorkWithDataBase
{
    class Program
    {
        static void Main(string[] args)
        {
            string conectionString = @"Data Source=.\SQLEXPRESS;Initial Catalog=usersdb;Integrated Security=True";

            string NAME = "Fedor";
            int AGE = 25;

            using (SqlConnection connection = new SqlConnection(conectionString))
            {
                connection.Open();
                SqlCommand command = new SqlCommand("INSERT INTO Users (Name, Age) VALUES (@name, @age)", connection);

                SqlParameter paramName = new SqlParameter("@name", NAME);
                command.Parameters.Add(paramName);

                SqlParameter paramAge = new SqlParameter("@age", AGE);
                command.Parameters.Add(paramAge);

                SqlParameter paramId = new SqlParameter()
                {
                    ParameterName = "@id",
                    SqlDbType = SqlDbType.Int,
                    Direction = ParameterDirection.Output 
                };

                command.Parameters.Add(paramId);

                Console.WriteLine("paramID = {0}", paramId);

            }

            Console.WriteLine("connection is closed"); 

            Console.ReadKey();
        }
    }
}
