using PlanningPoker.DataAccess.Models;

namespace PlanningPoker.Library.Models
{
    public class PlayerCard
    {
        public Player Player { get; set; }

        public CardCall Card { get; set; }
    }
}
