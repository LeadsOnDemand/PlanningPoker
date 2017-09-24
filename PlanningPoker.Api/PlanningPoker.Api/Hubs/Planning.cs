using Microsoft.AspNetCore.SignalR;
using PlanningPoker.Api.Models;
using PlanningPoker.DataAccess.Models;
using System;
using System.Collections;
using System.IO;
using System.Runtime.Serialization.Json;
using System.Text;
using System.Threading.Tasks;

namespace PlanningPoker.Api.Hubs
{
    public class Planning: Hub
    {
        private static Hashtable _groupConnectionsMap = new Hashtable();

        public async void CreateGroup(string message)
        {
            var game = Deserialize<Game>(message);

            _groupConnectionsMap.Add(game.Tag, Context.ConnectionId);

            await Clients.All.InvokeAsync("GameCreated", message);

            await Groups.AddAsync(Context.ConnectionId, game.Tag);
        }

        public async void JoinGroup(string message)
        {
            var joinGameRequest = Deserialize<JoinGameRequest>(message);

            _groupConnectionsMap.Add(joinGameRequest.Game.Tag, Context.ConnectionId);

            await Clients.Group(joinGameRequest.Game.Tag).InvokeAsync("PlayerJoined", message);
        }

        public async void CreateUserStory(string message)
        {
            var createUserStoryRequest = Deserialize<CreateUserStoryRequest>(message);
            
            await Clients.Group(createUserStoryRequest.Game.Tag).InvokeAsync("StoryCreated", message);
        }

        public async void CastCard(string message)
        {
            var selectCardRequest = Deserialize<SelectCardRequest>(message);

            await Clients.Group(selectCardRequest.GameTag).InvokeAsync("PlayerSelectedCard", selectCardRequest.Player.Name);
        }

        public async void RevealCards(string message)
        {
            var game = Deserialize<Game>(message);

            await Clients.Group(game.Tag).InvokeAsync("RevealCards", message);
        }

        public async void LeaveGroup(string message)
        {
            var joinGameRequest = Deserialize<JoinGameRequest>(message);

            var groupName = _groupConnectionsMap[Context.ConnectionId];
            if (groupName != null)
            {
                await Groups.RemoveAsync(Context.ConnectionId, groupName.ToString());
            }

            await Clients.Group(joinGameRequest.Game.Tag).InvokeAsync("GroupMemberLeft", message);
        }

        public override Task OnDisconnectedAsync(Exception exception)
        {
            var groupName = _groupConnectionsMap[Context.ConnectionId];
            if (groupName != null)
            { 
                Groups.RemoveAsync(Context.ConnectionId, groupName.ToString());
            }

            return base.OnDisconnectedAsync(exception);
        }

        private T Deserialize<T>(string json)
        {
            using (var ms = new MemoryStream(Encoding.Unicode.GetBytes(json)))
            {
                var deserializer = new DataContractJsonSerializer(typeof(T));
                return (T)deserializer.ReadObject(ms);
            }
        }
    }
}
