using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Hangman2
{
    public partial class Form1 : Form
    {
        HangmanWord hangman;
        SystemMonitor s = new SystemMonitor();
        public int TIME = 60 * 10;
        public int timeElapsed { get; set; }
        public Form1()
        {
            hangman = new HangmanWord();
            InitializeComponent();
            lblWord.Text= hangman.wordMask();
            tbVneseniBukvi.Text = hangman.usedLetters();
        }

        private void tbLetter_TextChanged(object sender, EventArgs e)
        {

        }

        private void button1_Click(object sender, EventArgs e)
        {
            char c = Convert.ToChar(tbLetter.Text);
            hangman.guessLetter(c);
            lblWord.Text = hangman.wordMask();
            tbVneseniBukvi.Text = hangman.usedLetters();
        }

        private void lblWord_Click(object sender, EventArgs e)
        {

        }

        private void button2_Click(object sender, EventArgs e)
        {
            s.ShowDialog();
        }

        private void timer1_Tick(object sender, EventArgs e)
        {
            timeElapsed++;
            pbVreme.Value = TIME - timeElapsed;
            updateTime();
        }
        public void updateTime()
        {
            int left = TIME - timeElapsed;
            int min = left / 60;
            int sec = left % 60;
            label2.Text = string.Format("{0:00}:{1:00}", min, sec);
        }
    }
}
