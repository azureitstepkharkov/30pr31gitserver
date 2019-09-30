namespace JavaNovaPosthaBot.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class UpdateUser : DbMigration
    {
        public override void Up()
        {
            AlterColumn("dbo.Users", "Name", c => c.String());
        }
        
        public override void Down()
        {
            AlterColumn("dbo.Users", "Name", c => c.Int(nullable: false));
        }
    }
}
