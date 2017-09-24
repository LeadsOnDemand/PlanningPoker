using System.Runtime.Serialization;

namespace PlanningPoker.DataAccess.Enums
{
    [DataContract(Name = "UserStoryStatus")]
    public enum UserStoryStatus
    {
        [EnumMember]
        OnGoingPlanning = 0,

        [EnumMember]
        CompletedPlanning =1,

        [EnumMember]
        Cancelled = 2
    }
}
