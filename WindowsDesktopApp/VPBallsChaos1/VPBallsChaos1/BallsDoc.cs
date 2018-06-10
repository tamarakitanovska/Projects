using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace VPBallsChaos1
{
    [Serializable]
    public class BallsDoc
    {
        List<Balls> list;
        public BallsDoc()
        {
            list = new List<Balls>();
        }
        public void Draw(Graphics g)
        {
            foreach(Balls b in list)
            {
                b.Draw(g);
            }
        }
        public void Colliding()
        {
            for(int i = 0; i < list.Count; i++)
            {
                for(int j = i + 1; j < list.Count; j++)
                {
                    if (list[i].isColliding(list[j]))
                    {
                        list[i].Flag = true;
                        list[j].Flag = true;
                    }
                }
            }
            for(int i = 0; i < list.Count; i++)
            {
                if (list[i].Flag == true)
                {
                    list.RemoveAt(i);
                }
            }
        }
        public void Move(int left, int top, int width, int height)
        {
            foreach(Balls b in list)
            {
                b.Move(left, top, width, height);
            }
        }
        public void Add(Balls b)
        {
            list.Add(b);
        }
    }
}
