namespace Hangman2
{
    partial class SystemMonitor
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.lblMemVal = new System.Windows.Forms.Label();
            this.lblProcVal = new System.Windows.Forms.Label();
            this.btnClose = new System.Windows.Forms.Button();
            this.btnRefresh = new System.Windows.Forms.Button();
            this.memoryCounter = new System.Diagnostics.PerformanceCounter();
            this.procesorCounter = new System.Diagnostics.PerformanceCounter();
            ((System.ComponentModel.ISupportInitialize)(this.memoryCounter)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.procesorCounter)).BeginInit();
            this.SuspendLayout();
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(13, 13);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(147, 15);
            this.label1.TabIndex = 0;
            this.label1.Text = "Slobodna memorija(bajti)";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(16, 60);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(147, 15);
            this.label2.TabIndex = 1;
            this.label2.Text = "Optovarenost na procesor";
            // 
            // lblMemVal
            // 
            this.lblMemVal.AutoSize = true;
            this.lblMemVal.Location = new System.Drawing.Point(264, 13);
            this.lblMemVal.Name = "lblMemVal";
            this.lblMemVal.Size = new System.Drawing.Size(14, 15);
            this.lblMemVal.TabIndex = 2;
            this.lblMemVal.Text = "?";
            // 
            // lblProcVal
            // 
            this.lblProcVal.AutoSize = true;
            this.lblProcVal.Location = new System.Drawing.Point(264, 56);
            this.lblProcVal.Name = "lblProcVal";
            this.lblProcVal.Size = new System.Drawing.Size(14, 15);
            this.lblProcVal.TabIndex = 3;
            this.lblProcVal.Text = "?";
            // 
            // btnClose
            // 
            this.btnClose.Location = new System.Drawing.Point(299, 8);
            this.btnClose.Name = "btnClose";
            this.btnClose.Size = new System.Drawing.Size(75, 23);
            this.btnClose.TabIndex = 4;
            this.btnClose.Text = "Close";
            this.btnClose.UseVisualStyleBackColor = true;
            this.btnClose.Click += new System.EventHandler(this.btnClose_Click);
            // 
            // btnRefresh
            // 
            this.btnRefresh.Location = new System.Drawing.Point(299, 51);
            this.btnRefresh.Name = "btnRefresh";
            this.btnRefresh.Size = new System.Drawing.Size(75, 23);
            this.btnRefresh.TabIndex = 5;
            this.btnRefresh.Text = "Refresh";
            this.btnRefresh.UseVisualStyleBackColor = true;
            this.btnRefresh.Click += new System.EventHandler(this.btnRefresh_Click);
            // 
            // memoryCounter
            // 
            this.memoryCounter.CategoryName = "Memory";
            this.memoryCounter.CounterName = "Available Bytes";
            // 
            // procesorCounter
            // 
            this.procesorCounter.CategoryName = "Processor";
            this.procesorCounter.CounterName = "% Processor Time";
            this.procesorCounter.InstanceName = "_Total";
            // 
            // SystemMonitor
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(386, 89);
            this.Controls.Add(this.btnRefresh);
            this.Controls.Add(this.btnClose);
            this.Controls.Add(this.lblProcVal);
            this.Controls.Add(this.lblMemVal);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Name = "SystemMonitor";
            this.Text = "SystemMonitor";
            this.Load += new System.EventHandler(this.SystemMonitor_Load);
            ((System.ComponentModel.ISupportInitialize)(this.memoryCounter)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.procesorCounter)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label lblMemVal;
        private System.Windows.Forms.Label lblProcVal;
        private System.Windows.Forms.Button btnClose;
        private System.Windows.Forms.Button btnRefresh;
        private System.Diagnostics.PerformanceCounter memoryCounter;
        private System.Diagnostics.PerformanceCounter procesorCounter;
    }
}