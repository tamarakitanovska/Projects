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

namespace VPBlowingBalons
{
    public partial class Form1 : Form
    {
        BallonsList ballonsList;
        private string FileName;
        public Form1()
        {
            InitializeComponent();
            ballonsList = new BallonsList();
            this.DoubleBuffered = true;
            timer1 = new Timer();
            timer1.Interval = 200;
            timer1.Tick += new EventHandler(timer1_Tick);
            timer1.Start();
        }


        private void Form1_Paint(object sender, PaintEventArgs e)
        {
            ballonsList.Draw(e.Graphics);
        }

        private void Form1_MouseDoubleClick(object sender, MouseEventArgs e)
        {
            ballonsList.AddBallon(new Point(e.X, e.Y));
            Invalidate(true);
        }

        private void timer1_Tick(object sender, EventArgs e)
        {
            ballonsList.BlowBallons();
            ballonsList.checkForExplosions();
            Invalidate(true);
        }

        private void statusStrip1_Paint(object sender, PaintEventArgs e)
        {
            labelTotal.Text = String.Format("Total: {0}", ballonsList.list.Count);
        }

        private void toolStripLabel1_Click(object sender, EventArgs e)
        {

        }

        private void toolStripLabel1_Click_1(object sender, EventArgs e)
        {

        }

        private void newToolStripMenuItem_Click(object sender, EventArgs e)
        {
            ballonsList = new BallonsList();
            Invalidate();
        }

        private void saveToolStripMenuItem_Click(object sender, EventArgs e)
        {
            saveFile();
        }
        private void saveFile()
        {
            if (FileName == null)
            {
                SaveFileDialog saveFileDialog = new SaveFileDialog();
                saveFileDialog.Filter = "Save ballons File (*.bal) | *.bal";
                saveFileDialog.Title = "Save ballons file";
                if (saveFileDialog.ShowDialog() == DialogResult.OK)
                {
                    FileName = saveFileDialog.FileName;
                }
            }
            if (FileName != null)
            {
                using (FileStream fileStream = new FileStream(FileName, FileMode.Create))
                {
                    IFormatter formatter = new BinaryFormatter();
                    formatter.Serialize(fileStream, ballonsList);
                }
            }
        }
        public void openFile()
        {
            OpenFileDialog openFileDialog = new OpenFileDialog();
            openFileDialog.Filter = "Ballons doc file (*.bal)|*.bal";
            openFileDialog.Title = "Open ballons file";
            if(openFileDialog.ShowDialog() == DialogResult.OK)
            {
                FileName = openFileDialog.FileName;
                try
                {
                    using(FileStream fileStream = new FileStream(FileName, FileMode.Open))
                    {
                        IFormatter formatter = new BinaryFormatter();
                        ballonsList = (BallonsList)formatter.Deserialize(fileStream);
                    }

                }
                catch(Exception ex)
                {
                    MessageBox.Show("Could not read file: " + FileName);
                    FileName = null;
                    return;
                }
                Invalidate(true);
            }
        }

        private void openToolStripMenuItem_Click(object sender, EventArgs e)
        {
            openFile();
        }

        private void Form1_Load(object sender, EventArgs e)
        {

        }

        private void Form1_FormClosing(object sender, FormClosingEventArgs e)
        {
            DialogResult result = MessageBox.Show("Save document and exit", "Save document?", MessageBoxButtons.YesNo);
            if(result == System.Windows.Forms.DialogResult.Cancel)
            {
                e.Cancel = true;
            }
            if(result == System.Windows.Forms.DialogResult.Yes)
            {
                saveFile();
            }
        }
    }
}
