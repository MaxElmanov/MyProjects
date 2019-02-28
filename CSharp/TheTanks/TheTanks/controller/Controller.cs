using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using System.Windows.Forms;
using TheTanks.model;

namespace TheTanks.controller
{
    public class Controller
    {
        MainForm_View view;
        Model model;

        Thread ThreadGameLoop;

        public Controller(MainForm_View view, Model model)
        {
            this.view = view;
            this.model = model;
            model.gameStatus = GameStatus.playing;
        }

        public void StartGame()
        {
            if (model.gameStatus == GameStatus.playing)
            {
                ThreadGameLoop = new Thread(model.GameLoop);
                ThreadGameLoop.Start();
                view.Invalidate(); // this funtion give start inertion for game
            }
        }
    }
}
