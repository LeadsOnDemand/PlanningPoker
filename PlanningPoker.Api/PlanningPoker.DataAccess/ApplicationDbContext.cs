using Microsoft.EntityFrameworkCore;
using PlanningPoker.DataAccess.Models;

namespace PlanningPoker.DataAccess
{
    public class ApplicationDbContext : DbContext
    {
        public ApplicationDbContext(DbContextOptions<ApplicationDbContext> options) 
            : base(options)
        {
            
        }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            base.OnModelCreating(modelBuilder);
            modelBuilder.Entity<Game>();
            modelBuilder.Entity<Player>();
            modelBuilder.Entity<CardCall>();
            modelBuilder.Entity<UserStory>();
        }
    }
}
