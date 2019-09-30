namespace JavaNovaPosthaBot.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class CreateReminder : DbMigration
    {
        public override void Up()
        {
            CreateTable(
                "dbo.Reminders",
                c => new
                    {
                        Id = c.Int(nullable: false, identity: true),
                        UserId = c.Int(nullable: false),
                        TTN = c.String(),
                        Status = c.String(),
                    })
                .PrimaryKey(t => t.Id)
                .ForeignKey("dbo.Users", t => t.UserId, cascadeDelete: true)
                .Index(t => t.UserId);
            
        }
        
        public override void Down()
        {
            DropForeignKey("dbo.Reminders", "UserId", "dbo.Users");
            DropIndex("dbo.Reminders", new[] { "UserId" });
            DropTable("dbo.Reminders");
        }
    }
}
