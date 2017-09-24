using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Hosting;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Logging;
using PlanningPoker.Api.Hubs;
using PlanningPoker.DataAccess;
using PlanningPoker.DataAccess.BaseClasses;
using PlanningPoker.Library.Services;
using System;

namespace PlanningPoker.Api
{
    public class Startup
    {
        public Startup(IHostingEnvironment env)
        {
            var builder = new ConfigurationBuilder()
                .SetBasePath(env.ContentRootPath)
                .AddJsonFile("appsettings.json", optional: false, reloadOnChange: true)
                .AddJsonFile($"appsettings.{env.EnvironmentName}.json", optional: true)
                .AddEnvironmentVariables();
            Configuration = builder.Build();
        }

        public IConfigurationRoot Configuration { get; }

        // This method gets called by the runtime. Use this method to add services to the container.
        public void ConfigureServices(IServiceCollection services)
        {
            services.AddDbContext<ApplicationDbContext>(_ => _.UseMySql(Configuration.GetConnectionString("PlanningPokerDB")));

            services.AddScoped(typeof(IRepository<>), typeof(Repository<>));
            services.AddTransient<IPlayerService, PlayerService>();
            services.AddTransient<IGameService, GameService>();
            services.AddTransient<IUserStoryService, UserStoryService>();
            services.AddTransient<ICardCallService, CardCallService>();
            services.AddTransient<IPlayerCardService, PlayerCardService>();

            services.AddCors(options =>
            {
                options.AddPolicy("AllowAllOrigins",
                builder =>
                {
                    builder.AllowAnyMethod().AllowAnyHeader().AllowAnyOrigin();
                });
            });

            // Add framework services.
            services.AddMvc();
            services.AddSignalR();
        }

        // This method gets called by the runtime. Use this method to configure the HTTP request pipeline.
        public void Configure(IApplicationBuilder app, IHostingEnvironment env, ILoggerFactory loggerFactory, IServiceProvider serviceProvider)
        {
            loggerFactory.AddConsole(Configuration.GetSection("Logging"));
            loggerFactory.AddDebug();

            app.UseCors("AllowAllOrigins");
            app.UseMvc();

            app.UseSignalR(routes =>
            {
                routes.MapHub<Planning>("planning");
            });

            var dbContext = serviceProvider.GetService<ApplicationDbContext>();
            dbContext.Database.EnsureCreated();
        }
    }
}
