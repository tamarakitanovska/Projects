using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace VPFlyingBalls1
{
    [Serializable]
    public class BallDoc
    {
        List<Ball> list;
        public int Width { get; set; }
        public static Random r = new Random();
        public int Height;
        public int Hits { get; set; }
        public int Misses { get; set; }
        public BallDoc(int width, int height)
        {
            list = new List<Ball>();
            Width = width;
            Height = height;
            Hits = 0;
            Misses = 0;
        }
        public void Draw(Graphics g)
        {
            foreach(Ball b in list)
            {
                b.Draw(g);
            }
        }
        public void Move()
        {
            foreach(Ball b in list)
            {
                b.Move(new Point(b.Center.X+10,b.Center.Y));
            }
            foreach(Ball b in list)
            {
                if(b.Center.X - Ball.RADIUS > Width)
                {
                    b.State = -1;
                }
            }
            for(int i=0;i<list.Count;i++)
            {
                if (list[i].State == -1)
                {
                    list.RemoveAt(i);
                    Misses++;
                }
            }
        }
        public void isHit(int x, int y)
        {
            foreach(Ball b in list)
            {
                if (b.isHit(x, y))
                {
                    b.State++;
                }
            }
            for(int i=0;i<list.Count;i++)
            {
                if (list[i].State == 3)
                {
                    list.RemoveAt(i);
                    Hits++;
                }
            }
        }
        public void Add()
        {
            list.Add(new Ball(new Point(-Ball.RADIUS, r.Next(2*Ball.RADIUS,Height - 2 * Ball.RADIUS))));
        }
    }
}
