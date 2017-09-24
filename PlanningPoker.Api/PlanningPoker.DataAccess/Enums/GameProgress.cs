using System.Runtime.Serialization;

namespace PlanningPoker.DataAccess.Enums
{
    [DataContract(Name = "GameProgress")]
    public enum GameProgress
    {
        [EnumMember]
        NotStarted = 0,

        [EnumMember]
        Started = 1,

        [EnumMember]
        Ended = 2
    }
}
