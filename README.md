# Leads On Demand Full Stack Coding Challenge
The goal of this coding challange is to guage how you attack certain problems. It is more important that you are able to defend your choices and even if you are unable to complete the challange you should be able to speak to what problems you forsee and any strategy to overcome them.

# Coding Chalange
We need a better planning tool and we have decided on [planning poker](https://en.wikipedia.org/wiki/Planning_poker). Your objective is to build a webapp that allows a single or multiple teams to plan using planning poker.
## Business Requirements
1. Multiple players must be able to join a game.
2. The game can only begin once there are more than two players.
3. A player must be able to join and leave at any time.
4. You need to reveal all players choices only after all players have selected an estimate.

## Technical Requirements
1. Must be a webapp (SPA) Feel free to use any library or framework you feel comfortable with or play with any new tech you have been looking to explore.
    * Please provide your target browser(s)
    * (Optional) wrap it in desktop framework such as [Electron](https://electron.atom.io/), totally up to you.
2. Backend can be in any language that can run on linux If you choose to use a backend for syncronization, there is always [WebRTC](https://webrtc.org/).
    * For .net devs, please use [.NET Core](https://www.microsoft.com/net/download/core) or [Mono](http://www.mono-project.com/).
    * Using WebRTC defeates the purpose of a full stack coding challange, but since this tech is so cool, we will not hold it against anyone who attempts it.
3. Communication from client to server must be over [http(s)://](https://en.wikipedia.org/wiki/Hypertext_Transfer_Protocol), [ws://](https://en.wikipedia.org/wiki/WebSocket) or any combination of the two.
    * You are free to use any architecture in your transport such as [SOAP](https://en.wikipedia.org/wiki/SOAP), [gRPC](https://grpc.io/), [REST](https://en.wikipedia.org/wiki/Representational_state_transfer), [GraphQL](https://facebook.github.io/react/blog/2015/05/01/graphql-introduction.html) or any other method you wish.
4. If you choose to use a database (not required) it must run on linux.
5. Please provide instructions on how to compile and run your project.

## Extra Credit
1. Containerize your solution with [Docker](https://www.docker.com/)
2. Orchestrate your containers with [Docker Compose](https://docs.docker.com/compose/)

## Submitions
In order to submit your solution please [fork](https://help.github.com/articles/fork-a-repo/) this project submit a [pull request](https://help.github.com/articles/creating-a-pull-request/).
If you have any questions please create an [issue](https://guides.github.com/features/issues/).
