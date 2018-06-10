using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace VPNarackaNaPica
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            calculatePrice();
        }
        public void calculatePrice()
        {
            int vkupno = 0;
            if (rbMala.Checked) {
                    vkupno += 200;
                }
            else if (rbSredna.Checked)
            {
                vkupno += 300;
            }
            else if (rbGolema.Checked)
            {
                vkupno += 500;
            }
           for(int i = 0; i < cblDodatoci.Items.Count; i++)
            {
                if (cblDodatoci.GetItemChecked(i))
                {
                    if (i == 0)
                    {
                        vkupno += Convert.ToInt32(tbDodatok1.Text);
                    }
                    if (i == 1)
                    {
                        vkupno += Convert.ToInt32(tbDodatok2.Text);
                    }
                    if(i==2)
                    {
                        vkupno += Convert.ToInt32(tbDodatok3.Text);
                    }
                }
            }
            if(tbKokaKola.Text!="")
            {
                int kolicina1 = Convert.ToInt32(tbKokaKola.Text);
                int plati1 = kolicina1 * Convert.ToInt32(tbCena1.Text);
                tbVkupno1.Text = plati1.ToString();
            }
            if (tbSokJabolko.Text!="")
            {
                int kolicina2 = Convert.ToInt32(tbSokJabolko.Text);
                int plati2 = kolicina2 * Convert.ToInt32(tbCena2.Text);
                tbVkupno2.Text = plati2.ToString();
            }
            if (tbPivo.Text!= "")
            {
                int kolicina3 = Convert.ToInt32(tbPivo.Text);
                int plati3 = kolicina3 * Convert.ToInt32(tbCena3.Text);
                tbVkupno3.Text = plati3.ToString();
            }
            if (tbVkupno1.Text != "")
            {
                if (tbKokaKola.Text != "")
                {
                    vkupno += Convert.ToInt32(tbVkupno1.Text);
                }
                else
                {
                    tbVkupno1.Text = "";
                }
            }
            if (tbVkupno2.Text != "")
            {
                if (tbSokJabolko.Text != "")
                {
                    vkupno += Convert.ToInt32(tbVkupno2.Text);
                }
                else
                {
                    tbVkupno2.Text = "";
                }
            }
            if (tbVkupno3.Text != "")
            {
                if (tbPivo.Text != "")
                {
                    vkupno += Convert.ToInt32(tbVkupno3.Text);
                }
                else
                {
                    tbVkupno3.Text = "";
                }
            }
            if (tbCenaDesert.Text!= "")
            {
                vkupno += Convert.ToInt32(tbCenaDesert.Text);
            }
            tbVkupno.Text = vkupno.ToString();
            if (tbNaplateno.Text != "")
            {
                int vrakjanje = 0;
                vrakjanje = Convert.ToInt32(tbNaplateno.Text) - Convert.ToInt32(tbVkupno.Text);
                tbVrakjanje.Text = vrakjanje.ToString();
            }
        }

        private void tbMala_TextChanged(object sender, EventArgs e)
        {
            calculatePrice();
        }

        private void tbSredna_TextChanged(object sender, EventArgs e)
        {
            calculatePrice();
        }

        private void tbGolema_TextChanged(object sender, EventArgs e)
        {
            calculatePrice();
        }

        private void cblDodatoci_SelectedIndexChanged(object sender, EventArgs e)
        {
            calculatePrice();
        }

        private void tbDodatok1_TextChanged(object sender, EventArgs e)
        {
            calculatePrice();
        }

        private void tbDodatok2_TextChanged(object sender, EventArgs e)
        {
            calculatePrice();
        }

        private void tbDodatok3_TextChanged(object sender, EventArgs e)
        {
            calculatePrice();
        }

        private void tbKokaKola_TextChanged(object sender, EventArgs e)
        {
            calculatePrice();
        }

        private void tbSokJabolko_TextChanged(object sender, EventArgs e)
        {
            calculatePrice();
        }

        private void tbPivo_TextChanged(object sender, EventArgs e)
        {
            calculatePrice();
        }

        private void tbCena1_TextChanged(object sender, EventArgs e)
        {
            calculatePrice();
        }

        private void tbCena2_TextChanged(object sender, EventArgs e)
        {
            calculatePrice();
        }

        private void tbCena3_TextChanged(object sender, EventArgs e)
        {
            calculatePrice();
        }

        private void tbVkupno1_TextChanged(object sender, EventArgs e)
        {
            calculatePrice();
        }

        private void tbVkupno2_TextChanged(object sender, EventArgs e)
        {
            calculatePrice();
        }

        private void tbVkupno3_TextChanged(object sender, EventArgs e)
        {
            calculatePrice();
        }

        private void tbCenaDesert_TextChanged(object sender, EventArgs e)
        {
            calculatePrice();
        }

        private void rbMala_CheckedChanged(object sender, EventArgs e)
        {
            calculatePrice();
        }

        private void rbSredna_CheckedChanged(object sender, EventArgs e)
        {
            calculatePrice();
        }

        private void rbGolema_CheckedChanged(object sender, EventArgs e)
        {
            calculatePrice();
        }

        private void tbNaplateno_TextChanged(object sender, EventArgs e)
        {
            if (tbNaplateno.Text != "")
            {
                calculatePrice();
            }
            else
            {
                tbVrakjanje.Text = "";
            }
        }

        private void button2_Click(object sender, EventArgs e)
        {
            DialogResult result = MessageBox.Show("Dali sakate da ja otkazete narackata?", "Otkazi naracka", MessageBoxButtons.YesNo);
            if (result == DialogResult.Yes)
            {
                Application.Exit();
            }
        }
    }
}
