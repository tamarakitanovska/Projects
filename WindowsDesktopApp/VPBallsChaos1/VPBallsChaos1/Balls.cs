using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace VPBallsChaos1
{
    [Serializable]
    public class Balls
    {
        public Point Center { get; set; }
        public Color Color { get; set; }
        public int Velocity = 10;
        public static int RADIUS = 20;
        public double VelocityX { get; set; }
        public double VelocityY { get; set; }
        public double Angle { get; set; }
        public bool Flag { get; set; }
        public Balls(Point Center, Color Color)
        {
            this.Center = Center;
            this.Color = Color;
            Random r = new Random();
            Angle = r.NextDouble() * 2 * Math.PI;
            VelocityX = Math.Cos(Angle) * Velocity;
            VelocityY = Math.Sin(Angle) * Velocity;
            Flag = false;
        }
        public void Draw(Graphics g)
        {
            Brush b = new SolidBrush(Color);
            g.FillEllipse(b, Center.X - RADIUS, Center.Y - RADIUS, RADIUS * 2, RADIUS * 2);
            b.Dispose();
        }
        public bool isColliding(Balls b)
        {
            double d = Math.Sqrt((Center.X - b.Center.X) * (Center.X - b.Center.X) + (Center.Y - b.Center.Y) * (Center.Y - b.Center.Y));
            return d <= RADIUS + RADIUS;
        }
        public void Move(int left, int top, int width, int height)
        {
            double nextX = Center.X + VelocityX;
            double nextY = Center.Y + VelocityY;
            if(nextX - RADIUS<=left || nextX + RADIUS >= width)
            {
                VelocityX = -VelocityX;
            }
            if(nextY - RADIUS <= top || nextY + RADIUS >= height)
            {
                VelocityY = -VelocityY;
            }
            Center = new Point((int)nextX, (int)nextY);
        }
    }
}
