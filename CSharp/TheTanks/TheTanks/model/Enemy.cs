using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TheTanks.model
{
    public class Enemy
    {
        int x, y, dx, dy, speed, size;
        int randSteps, countSteps;
        static Random random;

        Image img_enemy_top = Properties.Resources.enemy_top;
        Image img_enemy_right = Properties.Resources.enemy_right;
        Image img_enemy_left = Properties.Resources.enemy_left;
        Image img_enemy_down = Properties.Resources.enemy_down;

        Image img_enemy = Properties.Resources.enemy_down;

        public Enemy(int x, int y)
        {
            this.x = x;
            this.y = y;

            randSteps = 0;
            countSteps = 0;

            speed = 1;
            size = 32;

            dx = 0;
            dy = 1;

            random = new Random();

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

        public Image Img_enemy
        {
            get { return img_enemy; }
            private set { img_enemy = value; }
        }

        public void Run()
        {
            CollideBorders();

            GobySteps();

            SetImage();
        }

        void GobySteps()
        {
            if(countSteps < randSteps) 
            {
                if(dx == 1 || dx ==-1 && dy == 0) 
                {
                    x += dx * speed;
                }

                if (dy == 1 || dy == -1 && dx == 0)
                {
                    y += dy * speed;
                }

                countSteps++;
            }
            else
            {
                NewDirection();
            }
        }

        void NewDirection()
        {
            randSteps = random.Next(30, 70);
            countSteps = 0;
            int randDir = random.Next(1, 4); // 1=RIGHT; 2=LEFT; 3=TOP; 4=DOWN  

            switch (randDir)
            {
                //right
                case 1:
                    {
                        this.dx = 1;
                        this.dy = 0;
                        break;
                    }
                //left
                case 2:
                    {
                        this.dx = -1;
                        this.dy = 0;
                        break;
                    }
                //top
                case 3:
                    {
                        this.dx = 0;
                        this.dy = -1;
                        break;
                    }
                //down
                case 4:
                    {
                        this.dx = 0;
                        this.dy = 1;
                        break;
                    }

            }
        }

        public void SetDirection(int dx, int dy)
        {
            this.dx = dx;
            this.dy = dy;
        }

        private void SetImage()
        {
            //right
            if (dx == 1 && dy == 0) 
            {
                Img_enemy = img_enemy_right;
            }
            //left
            else if (dx == -1 && dy == 0) 
            {
                Img_enemy = img_enemy_left;
            }
            //top
            else if (dx == 0 && dy == -1) 
            {
                Img_enemy = img_enemy_top;
            }
            //down
            else if (dx == 0 && dy == 1) 
            {
                Img_enemy = img_enemy_down;
            }
        }

        private void CollideBorders()
        {
            if (x < -16)
            {
                x = 580;
            }
            else if (y < -16)
            {
                y = 580;
            }
            else if (x > 580)
            {
                x = -16;
            }
            else if (y > 580)
            {
                y = -16;
            }
        }

        public void CollideWalls(Wall wall)
        {
            if (this.x < wall.X + wall.Size &&
               this.x + this.size > wall.X &&
               this.y < wall.Y + wall.Size &&
               this.size + this.y > wall.Y)
            {
                if (dx == 1)
                {
                    x -= 5;
                    dx = 0;
                }
                else if (dx == -1)
                {
                    x += 5;
                    dx = 0;
                }
                else if (dy == 1)
                {
                    y -= 5;
                    dy = 0;
                }
                else if (dy == -1)
                {
                    y += 5;
                    dy = 0;
                }
            }
        }

        public bool CollideBullets(Bullet bullet)
        {
            if (this.x < bullet.X + bullet.Size &&
               this.x + this.size > bullet.X &&
               this.y < bullet.Y + bullet.Size &&
               this.size + this.y > bullet.Y)
            {
                return true;
            }

            return false;
        }

        //private int GetNumExceptZero(int min, int max)
        //{
        //    Random rand = new Random();

        //    int result = (rand.Next(0, 1) == 0) ? -1 : 1;

        //    return result;
        //}
    }
}
