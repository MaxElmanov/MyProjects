using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TheTanks.model
{
    public class Bullet
    {
        int x, y, dx, dy, speed, size;
        string statusBullet;

        Image img_bullet_top = Properties.Resources.bullet_top;
        Image img_bullet_right = Properties.Resources.bullet_right;
        Image img_bullet_left = Properties.Resources.bullet_left;
        Image img_bullet_down = Properties.Resources.bullet_down;

        Image img_bullet = Properties.Resources.bullet_top;

        public Bullet(int x, int y, int dx, int dy, string statusBullet)
        {
            this.x = x;
            this.y = y;

            this.statusBullet = statusBullet;

            speed = 8;
            size = 16;

            this.dx = dx;
            this.dy = dy;

            SetImage();
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

        public string StatusBullet
        {
            get { return statusBullet; }
            set { statusBullet = value; }
        }

        //public int Dy
        //{
        //    get { return dy; }
        //    set { dy = value; }
        //}

        //public int Dx
        //{
        //    get { return dx; }
        //    set { dx = value; }
        //}

        public Image Img_bullet
        {
            get { return img_bullet; }
            private set { img_bullet = value; }
        }

        public void Run()
        {
            x += dx * speed;
            y += dy * speed;

            SetImage();
        }

        public void SetImage() 
        {
            //right
            if (dx == 1 && dy == 0)
            {
                Img_bullet = img_bullet_right;
            }
            //left
            else if (dx == -1 && dy == 0)
            {
                Img_bullet = img_bullet_left;
            }
            //top
            else if (dx == 0 && dy == -1)
            {
                Img_bullet = img_bullet_top;
            }
            //down
            else if (dx == 0 && dy == 1)
            {
                Img_bullet = img_bullet_down;
            }
        }

        public bool isCollideBorders()
        {
            if (x < -16 ||y < -16 || x > 580 || y > 580)
            {
                return true;
            }

            return false;
        }

        public bool isCollideWalls(Wall wall)
        {
            if (this.x < wall.X + wall.Size &&
               this.x + this.size > wall.X &&
               this.y < wall.Y + wall.Size &&
               this.size + this.y > wall.Y)
            {
                return true;
            }

            return false;
        }
    }
}
