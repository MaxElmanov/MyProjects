
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace ImageBlurFilter
{
    public static class Matrix
    {
        public static double[,] GaussianBlur3x3
        {
            get
            {
                return new double[,]  
                { { 1, 2, 1, }, 
                  { 2, 4, 2, }, 
                  { 1, 2, 1, },
                };
            }
        }

        //public static double[,] GaussianBlur5x5
        //{
        //    get
        //    {
        //        return new double[,]  
        //        { { 2,  4,  5,  4, 2 }, 
        //          { 4,  9, 12,  9, 4 }, 
        //          { 5, 12, 15, 12, 5 },
        //          { 4,  9, 12,  9, 4 },
        //          { 2,  4,  5,  4, 2 },
        //        };
        //    }
        //}
    }
}

