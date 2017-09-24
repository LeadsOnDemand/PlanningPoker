using PlanningPoker.DataAccess.BaseClasses;
using PlanningPoker.DataAccess.Enums;
using System.ComponentModel.DataAnnotations;

namespace PlanningPoker.DataAccess.Models
{
    public class Game : BaseEntity
    {
        [MaxLength(100)]
        public string Tag { get; set; }

        [MaxLength(50)]
        public string Moderator { get; set; }

        [EnumDataType(typeof(GameProgress))]
        public GameProgress Progress { get; set; }

    }
}
