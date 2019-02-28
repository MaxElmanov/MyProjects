using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TheTanks.model
{
    public class Wall
    {
        int x, y, size;

        Image img_wall = Properties.Resources.wall;

        public Wall(int x, int y)
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
            get { return img_wall; }
            set { img_wall = value; }
        }
    }
}
