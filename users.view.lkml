view: users {
  # Source table definition
  sql_table_name: public.users ;;
  
  # Unique identifier for the view
  dimension: id {
    primary_key: yes
    type: number
    sql: ${TABLE}.id ;;
  }
  
  # String dimension example
  dimension: first_name {
    type: string
    sql: ${TABLE}.first_name ;;
    description: "User's first name"
  }
  
  dimension: last_name {
    type: string
    sql: ${TABLE}.last_name ;;
    description: "User's last name"
  }
  
  # Date dimension example
  dimension_group: created {
    type: time
    timeframes: [
      raw,
      time,
      date,
      week,
      month,
      quarter,
      year
    ]
    sql: ${TABLE}.created_at ;;
    description: "Timestamp when the user was created"
  }
  
  # Boolean dimension example
  dimension: is_active {
    type: yesno
    sql: ${TABLE}.is_active ;;
    description: "Whether the user account is active"
  }
  
  # Number dimension example
  dimension: age {
    type: number
    sql: ${TABLE}.age ;;
    description: "User's age"
  }
  
  # Case/When dimension example
  dimension: age_tier {
    type: tier
    tiers: [0, 18, 25, 35, 50, 65]
    style: integer
    sql: ${age} ;;
    description: "Age grouped into tiers"
  }
  
  # Derived dimension example
  dimension: full_name {
    type: string
    sql: CONCAT(${first_name}, ' ', ${last_name}) ;;
    description: "User's full name"
  }
}