using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TheTanks.model
{
    public class Tree
    {
        int x, y, size;

        Image img_tree = Properties.Resources.derevo;

        public Tree(int x, int y)
        {
            this.x = x;
            this.y = y;
            size = 32;
        }

        public int Y
        {
            get { return y; }
            set { y = value; }
        }
        public int X
        {
            get { return x; }
            set { x = value; }
        }

        public int Size
        {
            get { return size; }
            set { size = value; }
        }

        public Image Img_tree
        {
            get { return img_tree; }
            set { img_tree = value; }
        }
    }
}
