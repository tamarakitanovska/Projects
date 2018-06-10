using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Hangman2
{
    public class HangmanWord
    {
        public string Word { get; set; }
        public int numWrong { get; set; }
        public int maxWrong { get; set; }
        public List<char> VneseniBukvi { get; set; }
        public List<char> WordLetters { get; set; }
        public HangmanWord()
        {
            Word = "Macedonia";
            numWrong = 0;
            maxWrong = 5;
            VneseniBukvi = new List<char>();
            WordLetters = new List<char>();
        }
        public string wordMask()
        {
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < Word.Length; i++)
            {
                if (WordLetters.Contains(Word[i]))
                {
                    sb.Append(Word[i]);
                }
                else
                {
                    sb.Append("_");
                }
                sb.Append(" ");
            }
            return sb.ToString();
        }
        public void guessLetter(char letter)
        {
            if (VneseniBukvi.Contains(letter))
            {
                return;
            }
            else if (Word.Contains(letter))
            {
                WordLetters.Add(letter);
                wordMask();
            }
            else
            {
                numWrong++;
                VneseniBukvi.Add(letter);
                usedLetters();
            }
        }
        public string usedLetters()
        {
            StringBuilder sb = new StringBuilder();
            for(char c = 'a'; c < 'z'; c++)
            {
                if (VneseniBukvi.Contains(c))
                {
                    sb.Append(c);
                }
                else
                {
                    sb.Append("_");
                }
                sb.Append(" ");
            }
            return sb.ToString();
        }
    }
}
