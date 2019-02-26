using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using System.Windows.Forms;
using TheTanks.controller;
using TheTanks.model;

namespace TheTanks
{
    //public delegate void DShowGameOver();

    public partial class MainForm_View : Form
    {
        private event EventHandler eShowGameOver;
        //private DShowGameOver dShowGameOver;

        Model model;

        public MainForm_View()
        {
            InitializeComponent();

            //dShowGameOver = new DShowGameOver(ShowGameOver);
            //eShowGameOver += MainForm_View_eShowGameOver;

            model = new Model(eShowGameOver);

            Controller controller = new Controller(this, model);
            controller.StartGame();
        }

        //void MainForm_View_eShowGameOver(object sender, EventArgs e)
        //{
        //    Invoke(dShowGameOver); //Invoke appeals some delegate asynchronously
        //}

        //private void ShowGameOver()
        //{
        //    MessageBox.Show("Game Over", model.player.Life.ToString(), MessageBoxButtons.OK, MessageBoxIcon.None);
        //}

        private void MainForm_View_PreviewKeyDown(object sender, PreviewKeyDownEventArgs e)
        {
            switch ((char)e.KeyData)
            {
                //Up = W
                case 'W':
                    {
                        model.player.SetDirection(0, -1);
                        break;
                    }
                //Down = S
                case 'S':
                    {
                        model.player.SetDirection(0, 1);
                        break;
                    }
                //Right = D
                case 'D':
                    {
                        model.player.SetDirection(1, 0);
                        break;
                    }
                //Left = A
                case 'A':
                    {
                        model.player.SetDirection(-1, 0);
                        break;
                    }
                //Space
                case ' ':
                    {
                        model.bullets.Add(new Bullet(model.player.X + 16 / 2,
                                                     model.player.Y + 16 / 2,
                                                     model.player.Dx,
                                                     model.player.Dy,
                                                     "player"));
                        break;
                    }
            }
        }

        protected override void OnPaint(PaintEventArgs e)
        {
            #region Drawing player
            e.Graphics.DrawImage(model.player.Img_player, model.player.X, model.player.Y, model.player.Size, model.player.Size);
            #endregion

            #region Drawing enemies
            for (var i = 0; i < model.enemies.Count; i++)
                e.Graphics.DrawImage(model.enemies[i].Img_enemy, model.enemies[i].X, model.enemies[i].Y, model.enemies[i].Size, model.enemies[i].Size);
            #endregion 

            #region Drawing trees
            for (var i = 0; i < model.trees.Count; i++)
                e.Graphics.DrawImage(model.trees[i].Img_tree, model.trees[i].X, model.trees[i].Y, model.trees[i].Size, model.trees[i].Size);
            #endregion

            #region Drawing walls
            for (var i = 0; i < model.walls.Count; i++)
                e.Graphics.DrawImage(model.walls[i].Img_tree, model.walls[i].X, model.walls[i].Y, model.walls[i].Size, model.walls[i].Size);
            #endregion

            #region Drawing bullets
            for (var i = 0; i < model.bullets.Count; i++) {
                e.Graphics.DrawImage(model.bullets[i].Img_bullet, model.bullets[i].X, model.bullets[i].Y, model.bullets[i].Size, model.bullets[i].Size);
            }
            #endregion


            if (model.gameStatus != GameStatus.playing)
            {
                return;
            }

            Thread.Sleep(60);
            Invalidate();
        }

        private void MainForm_View_FormClosing(object sender, FormClosingEventArgs e)
        {
             model.gameStatus = GameStatus.stopping;

            DialogResult dr = MessageBox.Show("Do you really want to close the game sesstion?",
                                              "Tanks",
                                              MessageBoxButtons.YesNo,
                                              MessageBoxIcon.Asterisk,
                                              MessageBoxDefaultButton.Button1,
                                              MessageBoxOptions.DefaultDesktopOnly);

            if (dr == DialogResult.Yes)
            {
                e.Cancel = false;
            }
            else
            {
                e.Cancel = true;
            }
        }
    }
}
