namespace JavaNovaPosthaBot.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class AddStatus : DbMigration
    {
        public override void Up()
        {
            AddColumn("dbo.Users", "Status", c => c.Int(nullable: false));
        }
        
        public override void Down()
        {
            DropColumn("dbo.Users", "Status");
        }
    }
}
