using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace TheTanks.model
{
    public class Model
    {
        public static int AMMOUNT_ENEMIES = 10;
        public static int AMMOUNT_TREES = 16;
        public static int AMMOUNT_WALLS = 12;
        public Player player;
        public GameStatus gameStatus;
        public List<Enemy> enemies;
        public List<Tree> trees;
        public List<Wall> walls;
        public List<Bullet> bullets;

        //EventHandler eChangeLifes; // event to change score

        public Model(EventHandler eChangeLifes)
        {
            //this.eChangeLifes = eChangeLifes;

            player = new Player();

            trees = new List<Tree>();
            CreateTrees();

            walls = new List<Wall>();
            CreateWalls();

            enemies = new List<Enemy>();
            CreateEnemies();

            bullets = new List<Bullet>();

            gameStatus = GameStatus.playing;
        }

        public void GameLoop()
        {
            while(gameStatus == GameStatus.playing)
            {
                Thread.Sleep(60);

                #region Player
                player.Run();
                for (var i = 0; i < walls.Count; i++)
                {
                    player.CollideWalls(walls[i]);
                }

                for (var i = 0; i < enemies.Count; i++)
                {
                    if (player.CollidedEnemies(enemies[i]))
                    {
                        player.Life -= 1;

                        gameStatus = (player.Life <= 0) ? GameStatus.losing : GameStatus.playing;

                        //eChangeLifes.Invoke(new Object(), EventArgs.Empty);

                        player.ChangeDirection(player.Dx, player.Dy);
                    }
                }
                #endregion

                #region Enemies
                for (int i = 0; i < enemies.Count; i++)
                {
                    enemies[i].Run();
                }

                for (int i = 0; i < walls.Count; i++)
                {
                    for (int j = 0; j < enemies.Count; j++)
                    {
                        enemies[j].CollideWalls(walls[i]);
                    }
                }

                for (int i = 0; i < enemies.Count; i++)
                {
                    for (int j = 0; j < bullets.Count; j++)
                    {
                        if (enemies[i].CollideBullets(bullets[j]))
                        {
                            if(bullets[j].StatusBullet == "player") 
                            {
                                player.Score += 1;
                            }

                            enemies.RemoveAt(i);
                            break;
                        }
                    }
                }
                #endregion

                #region Bullets
                for (int i = 0; i < bullets.Count; i++) 
                {
                    bullets[i].Run();

                    if (bullets[i].isCollideBorders())
                    {
                        bullets.RemoveAt(i);
                        break;
                    }

                    for (int j = 0; j < walls.Count; j++)
                    {
                        if (bullets[i].isCollideWalls(walls[j]))
                        {
                            bullets.RemoveAt(i);
                            break;
                        }
                    }
                }
                #endregion
            }


        }

        private void CreateEnemies()
        {
            int x, y;
            Random r = new Random();

            while (enemies.Count < AMMOUNT_ENEMIES)
            {
                x = r.Next(18) * 32;
                y = r.Next(12) * 32;

                for (int i = 0; i < trees.Count; i++) 
                {
                    for(int j = 0; j < walls.Count; j++) 
                    {
                        for (int k = 0; k < enemies.Count; k++)
                        {
                            while (x == trees[i].X   && y == trees[i].Y  ||
                                   x == walls[j].X   && y == walls[j].Y  ||
                                   x == enemies[k].X && y == enemies[k].Y)
                            {
                                x = r.Next(18) * 32;
                                y = r.Next(12) * 32;
                            }
                        }
                    }     
               }

                enemies.Add(new Enemy(x, y));
            }
        }

        private void CreateTrees()
        {
            int x, y, shift = 96;

            while (trees.Count < AMMOUNT_TREES)
            {
                for (int i = 0; i < 5; i++) //first row of trees(1)
                {
                    y = 4 * 32;
                    x = (i * 32) + shift;
                    trees.Add(new Tree(x, y));
                }
                for (int i = 8; i < 13; i++) //first row of trees(2)
                {
                    y = 4 * 32;
                    x = (i * 32) + shift;
                    trees.Add(new Tree(x, y));
                }

                for (int i = 0; i < 5; i++) //second row of trees(1)
                {
                    y = 8 * 32;
                    x = i * 32 + shift;
                    trees.Add(new Tree(x, y));
                }
                for (int i = 8; i < 13; i++) //second row of trees(2)
                {
                    y = 8 * 32;
                    x = i * 32 + shift;
                    trees.Add(new Tree(x, y));
                }
            }
        }

        private void CreateWalls()
        {
            int x, y, shift = 32;

            while (walls.Count < AMMOUNT_WALLS)
            {
                for (int i = 0; i < 2; i++) //first row of walls(1)
                {
                    y = 2 * 32;
                    x = (i * 32) + shift;
                    walls.Add(new Wall(x, y));
                }
                for (int i = 15; i < 17; i++) //first row of walls(2)
                {
                    y = 2 * 32;
                    x = (i * 32) + shift;
                    walls.Add(new Wall(x, y));
                }

                for (int i = 7; i < 10; i++) //second row of walls
                {
                    y = 6 * 32;
                    x = i * 32 + shift;
                    walls.Add(new Wall(x, y));
                }

                for (int i = 0; i < 3; i++) //third row of walls(1)
                {
                    y = 14 * 32;
                    x = i * 32 + shift;
                    walls.Add(new Wall(x, y));
                }
                for (int i = 14; i < 17; i++) //third row of walls(2)
                {
                    y = 14 * 32;
                    x = i * 32 + shift;
                    walls.Add(new Wall(x, y));
                }
            }
        }
    }
}
