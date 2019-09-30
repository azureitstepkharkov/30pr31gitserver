using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace JavaNovaPosthaBot.MyClasses
{
    public class Reminder
    {
        public int Id { get; set; }
        public int UserId { get; set; }
        public string TTN { get; set; }
        public string Status { get; set; }

        public int StatusId { get; set; }

        public Reminder( string TTN, string status,string userId)
        {
            UserId = Convert.ToInt32(userId);
            this.TTN = TTN;
            Status = status;
        }
        public Reminder()
        {

        }
    }
}
