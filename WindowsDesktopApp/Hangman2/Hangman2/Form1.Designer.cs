namespace Hangman2
{
    partial class Form1
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
            this.components = new System.ComponentModel.Container();
            this.label1 = new System.Windows.Forms.Label();
            this.button1 = new System.Windows.Forms.Button();
            this.tbLetter = new System.Windows.Forms.TextBox();
            this.lblVneseniBukvi = new System.Windows.Forms.Label();
            this.tbVneseniBukvi = new System.Windows.Forms.TextBox();
            this.pbGreski = new System.Windows.Forms.ProgressBar();
            this.pbVreme = new System.Windows.Forms.ProgressBar();
            this.lblWord = new System.Windows.Forms.Label();
            this.button2 = new System.Windows.Forms.Button();
            this.timer1 = new System.Windows.Forms.Timer(this.components);
            this.lblTime = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.SuspendLayout();
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(13, 13);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(28, 15);
            this.label1.TabIndex = 0;
            this.label1.Text = "Igra";
            // 
            // button1
            // 
            this.button1.Location = new System.Drawing.Point(138, 59);
            this.button1.Name = "button1";
            this.button1.Size = new System.Drawing.Size(97, 74);
            this.button1.TabIndex = 2;
            this.button1.Text = "Ok";
            this.button1.UseVisualStyleBackColor = true;
            this.button1.Click += new System.EventHandler(this.button1_Click);
            // 
            // tbLetter
            // 
            this.tbLetter.Font = new System.Drawing.Font("Microsoft Sans Serif", 40F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.tbLetter.Location = new System.Drawing.Point(16, 59);
            this.tbLetter.MaxLength = 1;
            this.tbLetter.Name = "tbLetter";
            this.tbLetter.Size = new System.Drawing.Size(100, 74);
            this.tbLetter.TabIndex = 11;
            this.tbLetter.WordWrap = false;
            this.tbLetter.TextChanged += new System.EventHandler(this.tbLetter_TextChanged);
            // 
            // lblVneseniBukvi
            // 
            this.lblVneseniBukvi.AutoSize = true;
            this.lblVneseniBukvi.Location = new System.Drawing.Point(16, 201);
            this.lblVneseniBukvi.Name = "lblVneseniBukvi";
            this.lblVneseniBukvi.Size = new System.Drawing.Size(91, 15);
            this.lblVneseniBukvi.TabIndex = 12;
            this.lblVneseniBukvi.Text = "Pogodeni bukvi";
            // 
            // tbVneseniBukvi
            // 
            this.tbVneseniBukvi.Location = new System.Drawing.Point(16, 234);
            this.tbVneseniBukvi.Name = "tbVneseniBukvi";
            this.tbVneseniBukvi.Size = new System.Drawing.Size(650, 20);
            this.tbVneseniBukvi.TabIndex = 13;
            // 
            // pbGreski
            // 
            this.pbGreski.Location = new System.Drawing.Point(16, 281);
            this.pbGreski.Name = "pbGreski";
            this.pbGreski.Size = new System.Drawing.Size(650, 23);
            this.pbGreski.TabIndex = 14;
            // 
            // pbVreme
            // 
            this.pbVreme.Location = new System.Drawing.Point(16, 320);
            this.pbVreme.Maximum = 600;
            this.pbVreme.Name = "pbVreme";
            this.pbVreme.Size = new System.Drawing.Size(650, 23);
            this.pbVreme.TabIndex = 5;
            // 
            // lblWord
            // 
            this.lblWord.AutoSize = true;
            this.lblWord.Location = new System.Drawing.Point(16, 164);
            this.lblWord.Name = "lblWord";
            this.lblWord.Size = new System.Drawing.Size(41, 15);
            this.lblWord.TabIndex = 16;
            this.lblWord.Text = "label3";
            this.lblWord.Click += new System.EventHandler(this.lblWord_Click);
            // 
            // button2
            // 
            this.button2.Location = new System.Drawing.Point(302, 83);
            this.button2.Name = "button2";
            this.button2.Size = new System.Drawing.Size(75, 23);
            this.button2.TabIndex = 17;
            this.button2.Text = "button2";
            this.button2.UseVisualStyleBackColor = true;
            this.button2.Click += new System.EventHandler(this.button2_Click);
            // 
            // timer1
            // 
            this.timer1.Enabled = true;
            this.timer1.Interval = 1000;
            this.timer1.Tick += new System.EventHandler(this.timer1_Tick);
            // 
            // lblTime
            // 
            this.lblTime.AutoSize = true;
            this.lblTime.Location = new System.Drawing.Point(518, 13);
            this.lblTime.Name = "lblTime";
            this.lblTime.Size = new System.Drawing.Size(0, 15);
            this.lblTime.TabIndex = 18;
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(451, 13);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(41, 15);
            this.label2.TabIndex = 19;
            this.label2.Text = "label2";
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(687, 387);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.lblTime);
            this.Controls.Add(this.button2);
            this.Controls.Add(this.lblWord);
            this.Controls.Add(this.pbVreme);
            this.Controls.Add(this.pbGreski);
            this.Controls.Add(this.tbVneseniBukvi);
            this.Controls.Add(this.lblVneseniBukvi);
            this.Controls.Add(this.tbLetter);
            this.Controls.Add(this.button1);
            this.Controls.Add(this.label1);
            this.Name = "Form1";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Button button1;
        private System.Windows.Forms.TextBox tbLetter;
        private System.Windows.Forms.Label lblVneseniBukvi;
        private System.Windows.Forms.TextBox tbVneseniBukvi;
        private System.Windows.Forms.ProgressBar pbGreski;
        private System.Windows.Forms.ProgressBar pbVreme;
        private System.Windows.Forms.Label lblWord;
        private System.Windows.Forms.Button button2;
        private System.Windows.Forms.Timer timer1;
        private System.Windows.Forms.Label lblTime;
        private System.Windows.Forms.Label label2;
    }
}

