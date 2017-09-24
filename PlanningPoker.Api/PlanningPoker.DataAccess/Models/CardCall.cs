using System;
using System.Collections.Generic;
using System.Text;
using PlanningPoker.DataAccess.BaseClasses;

namespace PlanningPoker.DataAccess.Models
{
    public class CardCall : BaseEntity
    {
        public Int64 UserStoryId { get; set; }

        public Int64 PlayerId { get; set; }

        public int? CardValue { get; set; }

    }
}
