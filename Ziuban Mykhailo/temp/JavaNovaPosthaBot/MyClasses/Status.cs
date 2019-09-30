using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace JavaNovaPosthaBot.MyClasses
{
    public class Status
    {
        public int Id { get; set; }
        public string Name { get; set; }

        public ICollection<Reminder> Reminders;

        public Status()
        {
            Reminders = new List<Reminder>();
        }
    }
}
