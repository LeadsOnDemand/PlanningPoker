using System;
using System.ComponentModel.DataAnnotations.Schema;

namespace PlanningPoker.DataAccess.BaseClasses
{
    public class BaseEntity
    {

        private DateTime? _addedDate;

        private DateTime? _modifiedDate;

        [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        public Int64 Id { get; set; }

        public DateTime AddedDate
        {
            get
            {
                return _addedDate.HasValue
                   ? _addedDate.Value
                   : DateTime.Now;
            }

            set { _addedDate = value; }
        }

        public DateTime ModifiedDate
        {
            get
            {
                return _modifiedDate.HasValue
                   ? _modifiedDate.Value
                   : DateTime.Now;
            }

            set { _modifiedDate = value; }
        }
    }
}
