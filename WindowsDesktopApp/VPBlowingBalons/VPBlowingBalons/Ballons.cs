using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace VPBlowingBalons
{
    [Serializable]
    public class Ballons
    {
        public Point Center { get; set; }
        public int Radius { get; set; }
        public Color Color { get; set; }
        public bool Flag { get; set; }
        public Ballons(Point Center,int Radius)
        {
            this.Center = Center;
            this.Radius = Radius;
            Random random = new Random();
            Color = Color.FromArgb(random.Next(255), random.Next(255), random.Next(255));
            Flag = false;
        }
        public void Draw(Graphics g)
        {
            Brush b = new SolidBrush(Color);
            g.FillEllipse(b, Center.X - Radius, Center.Y - Radius, 2 * Radius, 2 * Radius);
            b.Dispose();
        }
        public bool isTouching(Ballons b)
        {
            double distance = Math.Sqrt((Center.X - b.Center.X) * (Center.X - b.Center.X) + (Center.Y - b.Center.Y) * (Center.Y - b.Center.Y));
            return distance <= Radius + b.Radius;
        }
    }
}
