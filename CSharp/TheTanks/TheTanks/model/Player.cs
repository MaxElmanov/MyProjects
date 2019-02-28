using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;

namespace TheTanks.model
{
    public class Player
    {
        int x, y, dx, dy, speed, size, life, score;
       
        Image img_player_top = Properties.Resources.player_top;
        Image img_player_right = Properties.Resources.player_right;
        Image img_player_left = Properties.Resources.player_left;
        Image img_player_down = Properties.Resources.player_down;

        Image img_player;

        public Player()
        {
            x = 300;
            y = 500;

            size = 32;
            speed = 4;
            life = 3;

            dx = 0;
            dy = -1;

            img_player = Properties.Resources.player_top;//default

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

        public int Dy
        {
            get { return dy; }
            set { dy = value; }
        }
        public int Dx
        {
            get { return dx; }
            set { dx = value; }
        }

        public int Size
        {
            get { return size; }
            set { size = value; }
        }

        public int Life
        {
            get { return life; }
            set { life = value; }
        }

        public int Score
        {
            get { return score; }
            set { score = value; }
        }

        public Image Img_player
        {
            get { return img_player; }
            private set { img_player = value; }
        }

        public void Run()
        {
            CollideBorders();

            x += dx * speed;
            y += dy * speed;

            SetImage();
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
                Img_player = img_player_right;
            }
            //left
            else if (dx == -1 && dy == 0) 
            {
                Img_player = img_player_left;
            }
            //top
            else if (dx == 0 && dy == -1) 
            {
                Img_player = img_player_top;
            }
            //down
            else if (dx == 0 && dy == 1) 
            {
                Img_player = img_player_down;
            }
        }

        public void ChangeDirection(int dx, int dy)
        {
            if(dx == -1 && dy == 0) //left
            {
                x += 2;
            }
            else if (dx == 1 && dy == 0) //right
            {
                x -= 2;
            }
            else if (dy == -1 && dx == 0) //top
            {
                y += 2;
            }
            else if (dy == 1 && dx == 0) //down
            {
                y -= 2;
            }

            this.Dx *= -1;
            this.Dy *= -1;
        }

        private void CollideBorders()
        {
            if(x < 0) {
                x += 1;
                dx = 0;
            }
            else if (y < 0)
            {
                y += 1;
                dy = 0;
            }
            else if (x > 580)
            {
                x -= 1;
                dx = 0;
            }
            else if (y > 580)
            {
                y -= 1;
                dy = 0;
            }
        }

        public void CollideWalls(Wall wall)
        {
            if (this.x < wall.X + wall.Size &&
               this.x + this.size > wall.X  &&
               this.y < wall.Y + wall.Size  &&
               this.size + this.y > wall.Y)
            {
                if(dx == 1) {
                    x -= 3;
                    dx = 0;
                }
                else if (dx == -1){
                    x += 3;
                    dx = 0;
                }
                else if (dy == 1)
                {
                    y -= 3;
                    dy = 0;
                }
                else if (dy == -1)
                {
                    y += 3;
                    dy = 0;
                }
            }
        }

        public bool CollidedEnemies(Enemy enemy)
        {
            if (this.x < enemy.X + enemy.Size &&
                this.x + this.size > enemy.X  &&
                this.y < enemy.Y + enemy.Size &&
                this.size + this.y > enemy.Y)
            {
                return true;
            }

            return false;
        }

    }
}
