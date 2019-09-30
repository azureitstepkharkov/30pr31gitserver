using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace JavaNovaPosthaBot.MyClasses
{
    public class User
    {
        public int Id { get; set; }
        public string Name { get; set; }
        public int Telegram { get; set; }

        public int Status { get; set; }

        public ICollection<Reminder> Reminders;

        public User(int telegram)
        {
            Status = 1;
            Telegram = telegram;
            Reminders = new List<Reminder>();
        }
        public User()
        {
            Reminders = new List<Reminder>();
        }
    }
}
