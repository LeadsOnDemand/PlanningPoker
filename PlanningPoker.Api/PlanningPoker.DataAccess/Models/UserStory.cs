using PlanningPoker.DataAccess.BaseClasses;
using System;
using System.ComponentModel.DataAnnotations;
using PlanningPoker.DataAccess.Enums;

namespace PlanningPoker.DataAccess.Models
{
    public class UserStory : BaseEntity
    {
        public Int64 GameId { get; set; }

        [MaxLength(5000)]
        public string ShortOverview { get; set; }

        [MaxLength(5000)]
        public string Summary { get; set; }

        [EnumDataType(typeof(UserStoryStatus))]
        public UserStoryStatus Status { get; set; }

        public string Title { get; set; }
    }
}
