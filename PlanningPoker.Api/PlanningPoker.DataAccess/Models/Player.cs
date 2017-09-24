using System;
using System.ComponentModel.DataAnnotations;
using PlanningPoker.DataAccess.BaseClasses;

namespace PlanningPoker.DataAccess.Models
{
    public class Player : BaseEntity
    {
        public Int64 GameId { get; set; }

        [MaxLength(100)]
        public string Name { get; set; }

        public bool IsActive { get; set; }
    }
}
