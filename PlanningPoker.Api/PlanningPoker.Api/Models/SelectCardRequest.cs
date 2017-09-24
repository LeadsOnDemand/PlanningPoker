using PlanningPoker.DataAccess.Models;

namespace PlanningPoker.Api.Models
{
    public class SelectCardRequest
    {
        public UserStory UserStory { get; set; }

        public Player Player { get; set; }

        public int CardValue { get; set; }

        public string  GameTag { get; set; }

    }
}
