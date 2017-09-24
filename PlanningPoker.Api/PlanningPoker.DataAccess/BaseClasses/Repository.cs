using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Linq.Expressions;
using System.Threading.Tasks;

namespace PlanningPoker.DataAccess.BaseClasses
{
    public class Repository<T> : IRepository<T> where T : BaseEntity
    {
        private readonly ApplicationDbContext _context;
        private DbSet<T> _entities;

        public Repository(ApplicationDbContext context)
        {
            _context = context;
            _entities = context.Set<T>();
        }

        public async Task<List<T>> GetAllAsync()
        {
            await OpenDbConnection();

            return await _entities.AsNoTracking().ToListAsync();
        }

        public async Task<T> GetByIdAsync(long id)
        {
            await OpenDbConnection();

            return await _entities.SingleOrDefaultAsync(s => s.Id == id);
        }

        public async Task<List<T>> FindByAsync(Expression<Func<T, bool>> predicate)
        {
            await OpenDbConnection();

            return await _entities.AsNoTracking().Where(predicate).ToListAsync();
        }

        public async Task<int> InsertAsync(T entity)
        {
            if (entity == null)
            {
                throw new ArgumentNullException("entity");
            }

            _entities.Add(entity);

            await OpenDbConnection();

            return await _context.SaveChangesAsync();
        }

        public async Task<int> UpdateAsync(T entity)
        {
            if (entity == null)
            {
                throw new ArgumentNullException("entity");
            }

            entity.ModifiedDate = DateTime.Now;
            _entities.Update(entity);

            await OpenDbConnection();

            return await _context.SaveChangesAsync();
        }

        public async Task<int> DeleteAsync(T entity)
        {
            if (entity == null)
            {
                throw new ArgumentNullException("entity");
            }

            _entities.Remove(entity);

            await OpenDbConnection();

            return await _context.SaveChangesAsync();
        }
     
        private async Task OpenDbConnection()
        {
            if (_context != null && _context.Database != null && _context.Database.GetDbConnection() != null && 
                _context.Database.GetDbConnection().State == ConnectionState.Closed)
            {
                await _context.Database.OpenConnectionAsync();
            }
        }
    }
}
