using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace VPFlyingBalls1
{
    [Serializable]
    public class Ball
    {
        public Point Center { get; set; }
        public int State { get; set; }
        public static int RADIUS = 40;
        public static Random r = new Random();
        public Ball(Point Center)
        {
            this.Center = Center;
            State = r.Next(3);
        }
        public void Draw(Graphics g)
        {
            if (State == 2)
            {
                Brush b = new SolidBrush(Color.Green);
                g.FillEllipse(b, Center.X - RADIUS, Center.Y - RADIUS, 2 * RADIUS, 2 * RADIUS);
            }
            if (State == 1)
            {
                Brush b = new SolidBrush(Color.Blue);
                g.FillEllipse(b, Center.X - RADIUS, Center.Y - RADIUS, 2 * RADIUS, 2 * RADIUS);
                
            }
            if (State == 0)
            {
                Brush b = new SolidBrush(Color.Red);
                g.FillEllipse(b, Center.X - RADIUS, Center.Y - RADIUS, 2 * RADIUS, 2 * RADIUS);
                
            }
        }
        public void Move(Point newCenter)
        {
            Center = newCenter;
        } 
        public bool isHit(int x, int y)
        {
            double d = Math.Sqrt((Center.X - x) * (Center.X - x) + (Center.Y - y) + (Center.Y - y));
            return d <= RADIUS;
        }
    }
}
