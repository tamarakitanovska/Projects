using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Diagnostics;
namespace Hangman2
{
    public partial class SystemMonitor : Form
    {
        private PerformanceCounter memoryCounter1 = new PerformanceCounter("Memory", "Available Bytes");
        private PerformanceCounter processorCounter1 = new PerformanceCounter("Processor", "% Processor Time", "_Total");
        public SystemMonitor()
        {
            InitializeComponent();
        }

        private void SystemMonitor_Load(object sender, EventArgs e)
        {
            this.Text = System.Environment.MachineName;
        }

        private void btnClose_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        private void btnRefresh_Click(object sender, EventArgs e)
        {
            lblMemVal.Text = memoryCounter.NextValue().ToString("#,000");
            lblProcVal.Text = String.Format("{0:0.0}", procesorCounter.NextValue());
        }
    }
}
