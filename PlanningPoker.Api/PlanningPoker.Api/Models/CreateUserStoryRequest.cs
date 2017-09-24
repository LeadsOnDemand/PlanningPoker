using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using PlanningPoker.DataAccess.Models;

namespace PlanningPoker.Api.Models
{
    public class CreateUserStoryRequest
    {

        public UserStory UserStory { get; set; }

        public Game Game { set; get; }
    }
}
