using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Runtime.Serialization;
using System.Runtime.Serialization.Formatters.Binary;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace VPFlyingBalls1
{
    public partial class Form1 : Form
    {
        public BallDoc ballDoc { get; set; }
        public int timerTick { get; set; }
        public string FileName { get; set; }
        public int width { get; set; }
        public int height { get; set; }
        public Form1()
        {
            InitializeComponent();
            this.DoubleBuffered = true;
            ballDoc = new BallDoc(this.Width,this.Height);
            timerTick = 0;
            timer1.Interval = 200;
            timer1.Start();
            width = this.Width;
            height = this.Height;
        }

        private void Form1_Paint(object sender, PaintEventArgs e)
        {
            e.Graphics.Clear(Color.White);
            ballDoc.Draw(e.Graphics);
        }

        private void Form1_Click(object sender, EventArgs e)
        {
            
        }

        private void timer1_Tick(object sender, EventArgs e)
        {
            timerTick++;
            if (timerTick % 10 == 0)
            {
                ballDoc.Add();
            }
            ballDoc.Move();
            Invalidate(true);
        }

        private void Form1_MouseClick(object sender, MouseEventArgs e)
        {
            ballDoc.isHit(e.X, e.Y);
            Invalidate(true);
        }

        private void statusStrip1_Paint(object sender, PaintEventArgs e)
        {
            
        }

        private void toolStripStatusLabel1_Click(object sender, EventArgs e)
        {
            
        }

        private void statusStrip1_Paint_1(object sender, PaintEventArgs e)
        {
            label1.Text = String.Format("Hits: {0}", ballDoc.Hits);
            label2.Text = String.Format("Misses: {0}", ballDoc.Misses);
        }
        public void saveFile()
        {
            if(FileName == null)
            {
                SaveFileDialog saveFileDialog = new SaveFileDialog();
                saveFileDialog.Filter = "Save file doc (*.bal) | *.bal";
                saveFileDialog.Title = "Save file doc";
                if (saveFileDialog.ShowDialog() == DialogResult.OK)
                {
                    FileName = saveFileDialog.FileName;
                }
            }
            if (FileName != null)
            {
                using(FileStream fileStream = new FileStream(FileName, FileMode.Create))
                {
                    IFormatter formatter = new BinaryFormatter();
                    formatter.Serialize(fileStream,ballDoc);
                }
            }
        }
        public void openFile()
        {
            OpenFileDialog openFileDialog = new OpenFileDialog();
            openFileDialog.Filter = "Open bal doc file (*.bal)|*.bal";
            openFileDialog.Title = "Open bal doc";
            if (openFileDialog.ShowDialog() == DialogResult.OK)
            {
                FileName = openFileDialog.FileName;
                try
                {
                    using(FileStream fileStream = new FileStream(FileName, FileMode.Open))
                    {
                        IFormatter formatter = new BinaryFormatter();
                        ballDoc = (BallDoc)formatter.Deserialize(fileStream);
                    }
                }
                catch (Exception ex)
                {
                    MessageBox.Show("Could not open file");
                    FileName = null;
                    return;
                }
            }
        }

        private void saveToolStripMenuItem_Click(object sender, EventArgs e)
        {
            Pause();
            saveFile();
            Resume();
        }

        private void openToolStripMenuItem_Click(object sender, EventArgs e)
        {
            Pause();
            openFile();
            Resume();
        }
        public void Pause()
        {
            timer1.Stop();
        }
        public void Resume()
        {
            timer1.Start();
        }

        private void Form1_Resize(object sender, EventArgs e)
        {
            width = this.Width;
            height = this.Height;
        }
    }
}
