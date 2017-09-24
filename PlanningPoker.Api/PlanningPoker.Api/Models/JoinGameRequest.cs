using PlanningPoker.DataAccess.Models;

namespace PlanningPoker.Api.Models
{
    public class JoinGameRequest
    {
        public Player Player { get; set; }

        public Game Game { get; set; }

        //Needed during leaving group.
        public bool IsModerator { get; set; }
    }
}
