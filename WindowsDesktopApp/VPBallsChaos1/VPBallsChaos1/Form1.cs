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

namespace VPBallsChaos1
{
    public partial class Form1 : Form
    {
        public BallsDoc ballslDoc { get; set; }
        public Color Color { get; set; }
        private int leftX = 20;
        private int topY = 60;
        private int width;
        private int height;
        public string FileName { get; set; }
        public Form1()
        {
            InitializeComponent();
            this.DoubleBuffered = true;
            ballslDoc = new BallsDoc();
            Color = Color.Red;
            width = this.Width - (3 * leftX);
            height = this.Height - (int)(2.5 * topY);
            timer1.Interval = 50;
            timer1.Start();
        }

        private void Form1_Paint(object sender, PaintEventArgs e)
        {
            e.Graphics.Clear(Color.White);
            ballslDoc.Draw(e.Graphics);
            Pen p = new Pen(Color.Black, 3);
            e.Graphics.DrawRectangle(p, leftX, topY, width, height);
            p.Dispose();
        }

        private void Form1_MouseClick(object sender, MouseEventArgs e)
        {
            Balls b = new Balls(e.Location, Color);
            ballslDoc.Add(b);
            Invalidate();
        }

        private void timer1_Tick(object sender, EventArgs e)
        {
            ballslDoc.Move(leftX, topY, width, height);
            ballslDoc.Colliding();
            Invalidate();
        }

        private void saveToolStripMenuItem_Click(object sender, EventArgs e)
        {
            saveFile();
        }
        public void saveFile()
        {
            if(FileName == null)
            {
                SaveFileDialog saveFileDialog = new SaveFileDialog();
                saveFileDialog.Filter = "Save balls doc file (*.bal)|*.bal";
                saveFileDialog.Title = "Balls doc file";
                if(saveFileDialog.ShowDialog() == DialogResult.OK)
                {
                    FileName = saveFileDialog.FileName;
                }
            }
            if (FileName != null)
            {
                using(FileStream fileStream = new FileStream(FileName, FileMode.Create))
                {
                    IFormatter formatter = new BinaryFormatter();
                    formatter.Serialize(fileStream, ballslDoc);
                }
                {

                }
            }
        }
        public void openFile()
        {
            OpenFileDialog openFileDialog = new OpenFileDialog();
            openFileDialog.Filter = "Open balls doc (*.bal)|*.bal";
            openFileDialog.Title = "Open balls doc";
            if(openFileDialog.ShowDialog() == DialogResult.OK)
            {
                FileName = openFileDialog.FileName;
                try
                {
                    using(FileStream fileStream = new FileStream(FileName, FileMode.Open))
                    {
                        IFormatter formatter = new BinaryFormatter();
                        ballslDoc = (BallsDoc)formatter.Deserialize(fileStream);
                    }
                }
                catch (Exception ex)
                {
                    MessageBox.Show("Could not open file " + FileName);
                    FileName = null;
                    return;
                }
            }
            Invalidate(true);
        }

        private void openToolStripMenuItem_Click(object sender, EventArgs e)
        {
            openFile();
        }

        private void colorToolStripMenuItem_Click(object sender, EventArgs e)
        {
            ColorDialog colorDialog = new ColorDialog();
            if (colorDialog.ShowDialog() == DialogResult.OK)
            {
                Color = colorDialog.Color;
            }
        }
    }
}
