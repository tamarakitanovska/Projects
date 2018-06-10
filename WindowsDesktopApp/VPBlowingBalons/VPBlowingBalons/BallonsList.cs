using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace VPBlowingBalons
{
    public class BallonsList
    {
        public List<Ballons> list;
        public int RADIUS;
        public BallonsList()
        {
            list = new List<Ballons>();
            Random random = new Random();
            RADIUS = random.Next(20, 30);
        }
        public void AddBallon(Point Center)
        {
            Ballons b;
            b = new Ballons(Center, RADIUS);
            list.Add(b);
        }
        public void Draw(Graphics g)
        {
            foreach(Ballons b in list)
            {
                b.Draw(g);
            }
        }
        public void BlowBallons()
        {
            foreach(Ballons b in list)
            {
                b.Radius += 5;
            }
        }
        public  void checkForExplosions()
        {
            for(int i = 0; i < list.Count; i++)
            {
                for(int j = i + 1; j < list.Count; j++)
                {
                    if (list[i].isTouching(list[j]))
                    {
                        list[i].Flag = true;
                        list[j].Flag = true;
                    }
                }
            }
            for (int i = 0; i < list.Count(); i++)
            {
                if (list[i].Flag == true)
                    list.RemoveAt(i);
            }
        }
    }
}
