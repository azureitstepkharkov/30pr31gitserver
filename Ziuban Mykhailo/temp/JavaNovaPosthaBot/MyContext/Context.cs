using JavaNovaPosthaBot.MyClasses;
using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace JavaNovaPosthaBot.MyContext
{
    class Context : DbContext
    {
        public Context() : base("NovaPoshta")
        {

        }

        public DbSet<User> Users { get; set; }
        public DbSet<Reminder> Reminders { get; set; }
        public DbSet<Status> Statuses { get; set; }
    }
}
