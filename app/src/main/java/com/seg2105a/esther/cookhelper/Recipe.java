/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.24.0-abedcd4 modeling language!*/
package com.seg2105a.esther.cookhelper;

import java.util.*;

public class Recipe
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Recipe Attributes
  private String title;
  private String description;
  private Double cookingTime;
  private String image;

  //Recipe Associations
  private RecipeSystem recipeSystem;
  private List<RecipeStep> recipeSteps;
  private List<Category> categories;
  private List<RecipeType> recipeTypes;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Recipe(String aTitle, String aDescription, Double aCookingTime, String aImage, RecipeSystem aRecipeSystem)
  {
    title = aTitle;
    description = aDescription;
    cookingTime = aCookingTime;
    image = aImage;
    boolean didAddSystem = setSystem(aRecipeSystem);
    if (!didAddSystem)
    {
      throw new RuntimeException("Unable to create recipe due to recipeSystem");
    }
    recipeSteps = new ArrayList<RecipeStep>();
    categories = new ArrayList<Category>();
    recipeTypes = new ArrayList<RecipeType>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setTitle(String aTitle)
  {
    boolean wasSet = false;
    title = aTitle;
    wasSet = true;
    return wasSet;
  }

  public boolean setDescription(String aDescription)
  {
    boolean wasSet = false;
    description = aDescription;
    wasSet = true;
    return wasSet;
  }

  public boolean setCookingTime(Double aCookingTime)
  {
    boolean wasSet = false;
    cookingTime = aCookingTime;
    wasSet = true;
    return wasSet;
  }

  public boolean setImage(String aImage)
  {
    boolean wasSet = false;
    image = aImage;
    wasSet = true;
    return wasSet;
  }

  public String getTitle()
  {
    return title;
  }

  public String getDescription()
  {
    return description;
  }

  public Double getCookingTime()
  {
    return cookingTime;
  }

  public String getImage()
  {
    return image;
  }

  public RecipeSystem getRecipeSystem()
  {
    return recipeSystem;
  }

  public RecipeStep getRecipeStep(int index)
  {
    RecipeStep aRecipeStep = recipeSteps.get(index);
    return aRecipeStep;
  }

  public List<RecipeStep> getRecipeSteps()
  {
    List<RecipeStep> newRecipeSteps = Collections.unmodifiableList(recipeSteps);
    return newRecipeSteps;
  }

  public int numberOfRecipeSteps()
  {
    int number = recipeSteps.size();
    return number;
  }

  public boolean hasRecipeSteps()
  {
    boolean has = recipeSteps.size() > 0;
    return has;
  }

  public int indexOfRecipeStep(RecipeStep aRecipeStep)
  {
    int index = recipeSteps.indexOf(aRecipeStep);
    return index;
  }

  public Category getCategory(int index)
  {
    Category aCategory = categories.get(index);
    return aCategory;
  }

  public List<Category> getCategories()
  {
    List<Category> newCategories = Collections.unmodifiableList(categories);
    return newCategories;
  }

  public int numberOfCategories()
  {
    int number = categories.size();
    return number;
  }

  public boolean hasCategories()
  {
    boolean has = categories.size() > 0;
    return has;
  }

  public int indexOfCategory(Category aCategory)
  {
    int index = categories.indexOf(aCategory);
    return index;
  }

  public RecipeType getRecipeType(int index)
  {
    RecipeType aRecipeType = recipeTypes.get(index);
    return aRecipeType;
  }

  public List<RecipeType> getRecipeTypes()
  {
    List<RecipeType> newRecipeTypes = Collections.unmodifiableList(recipeTypes);
    return newRecipeTypes;
  }

  public int numberOfRecipeTypes()
  {
    int number = recipeTypes.size();
    return number;
  }

  public boolean hasRecipeTypes()
  {
    boolean has = recipeTypes.size() > 0;
    return has;
  }

  public int indexOfRecipeType(RecipeType aRecipeType)
  {
    int index = recipeTypes.indexOf(aRecipeType);
    return index;
  }

  public boolean setSystem(RecipeSystem aRecipeSystem)
  {
    boolean wasSet = false;
    if (aRecipeSystem == null)
    {
      return wasSet;
    }

    RecipeSystem existingRecipeSystem = recipeSystem;
    recipeSystem = aRecipeSystem;
    if (existingRecipeSystem != null && !existingRecipeSystem.equals(aRecipeSystem))
    {
      existingRecipeSystem.removeRecipe(this);
    }
    recipeSystem.addRecipe(this);
    wasSet = true;
    return wasSet;
  }

  public static int minimumNumberOfRecipeSteps()
  {
    return 0;
  }

  public RecipeStep addRecipeStep(String aNumber, String aDescription, String aTimeRequired, boolean aCompleted)
  {
    return new RecipeStep(aNumber, aDescription, aTimeRequired, aCompleted, this);
  }

  public boolean addRecipeStep(RecipeStep aRecipeStep)
  {
    boolean wasAdded = false;
    if (recipeSteps.contains(aRecipeStep)) { return false; }
    Recipe existingRecipe = aRecipeStep.getRecipe();
    boolean isNewRecipe = existingRecipe != null && !this.equals(existingRecipe);
    if (isNewRecipe)
    {
      aRecipeStep.setRecipe(this);
    }
    else
    {
      recipeSteps.add(aRecipeStep);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeRecipeStep(RecipeStep aRecipeStep)
  {
    boolean wasRemoved = false;
    //Unable to remove aRecipeStep, as it must always have a recipe
    if (!this.equals(aRecipeStep.getRecipe()))
    {
      recipeSteps.remove(aRecipeStep);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addRecipeStepAt(RecipeStep aRecipeStep, int index)
  {  
    boolean wasAdded = false;
    if(addRecipeStep(aRecipeStep))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfRecipeSteps()) { index = numberOfRecipeSteps() - 1; }
      recipeSteps.remove(aRecipeStep);
      recipeSteps.add(index, aRecipeStep);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveRecipeStepAt(RecipeStep aRecipeStep, int index)
  {
    boolean wasAdded = false;
    if(recipeSteps.contains(aRecipeStep))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfRecipeSteps()) { index = numberOfRecipeSteps() - 1; }
      recipeSteps.remove(aRecipeStep);
      recipeSteps.add(index, aRecipeStep);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addRecipeStepAt(aRecipeStep, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfCategories()
  {
    return 0;
  }

  public boolean addCategory(Category aCategory)
  {
    boolean wasAdded = false;
    if (categories.contains(aCategory)) { return false; }
    categories.add(aCategory);
    if (aCategory.indexOfRecipe(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aCategory.addRecipe(this);
      if (!wasAdded)
      {
        categories.remove(aCategory);
      }
    }
    return wasAdded;
  }

  public boolean removeCategory(Category aCategory)
  {
    boolean wasRemoved = false;
    if (!categories.contains(aCategory))
    {
      return wasRemoved;
    }

    int oldIndex = categories.indexOf(aCategory);
    categories.remove(oldIndex);
    if (aCategory.indexOfRecipe(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aCategory.removeRecipe(this);
      if (!wasRemoved)
      {
        categories.add(oldIndex,aCategory);
      }
    }
    return wasRemoved;
  }

  public boolean addCategoryAt(Category aCategory, int index)
  {  
    boolean wasAdded = false;
    if(addCategory(aCategory))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCategories()) { index = numberOfCategories() - 1; }
      categories.remove(aCategory);
      categories.add(index, aCategory);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveCategoryAt(Category aCategory, int index)
  {
    boolean wasAdded = false;
    if(categories.contains(aCategory))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCategories()) { index = numberOfCategories() - 1; }
      categories.remove(aCategory);
      categories.add(index, aCategory);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addCategoryAt(aCategory, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfRecipeTypes()
  {
    return 0;
  }

  public boolean addRecipeType(RecipeType aRecipeType)
  {
    boolean wasAdded = false;
    if (recipeTypes.contains(aRecipeType)) { return false; }
    recipeTypes.add(aRecipeType);
    if (aRecipeType.indexOfRecipe(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aRecipeType.addRecipe(this);
      if (!wasAdded)
      {
        recipeTypes.remove(aRecipeType);
      }
    }
    return wasAdded;
  }

  public boolean removeRecipeType(RecipeType aRecipeType)
  {
    boolean wasRemoved = false;
    if (!recipeTypes.contains(aRecipeType))
    {
      return wasRemoved;
    }

    int oldIndex = recipeTypes.indexOf(aRecipeType);
    recipeTypes.remove(oldIndex);
    if (aRecipeType.indexOfRecipe(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aRecipeType.removeRecipe(this);
      if (!wasRemoved)
      {
        recipeTypes.add(oldIndex,aRecipeType);
      }
    }
    return wasRemoved;
  }

  public boolean addRecipeTypeAt(RecipeType aRecipeType, int index)
  {  
    boolean wasAdded = false;
    if(addRecipeType(aRecipeType))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfRecipeTypes()) { index = numberOfRecipeTypes() - 1; }
      recipeTypes.remove(aRecipeType);
      recipeTypes.add(index, aRecipeType);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveRecipeTypeAt(RecipeType aRecipeType, int index)
  {
    boolean wasAdded = false;
    if(recipeTypes.contains(aRecipeType))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfRecipeTypes()) { index = numberOfRecipeTypes() - 1; }
      recipeTypes.remove(aRecipeType);
      recipeTypes.add(index, aRecipeType);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addRecipeTypeAt(aRecipeType, index);
    }
    return wasAdded;
  }

  public void delete() {
      RecipeSystem placeholderRecipeSystem = recipeSystem;
      this.recipeSystem = null;
      placeholderRecipeSystem.removeRecipe(this);
      while (recipeSteps.size() > 0) {
          RecipeStep aRecipeStep = recipeSteps.get(recipeSteps.size() - 1);
          aRecipeStep.delete();
          recipeSteps.remove(aRecipeStep);
      }

      ArrayList<Category> copyOfCategories = new ArrayList<Category>(categories);
      categories.clear();
      for (Category aCategory : copyOfCategories) {
          aCategory.removeRecipe(this);
      }
      ArrayList<RecipeType> copyOfRecipeTypes = new ArrayList<RecipeType>(recipeTypes);
      recipeTypes.clear();
      for (RecipeType aRecipeType : copyOfRecipeTypes) {
          aRecipeType.removeRecipe(this);
      }
  }

  public String toString()
  {
    String outputString = "";
    return super.toString() + "["+
            "title" + ":" + getTitle()+ "," +
            "description" + ":" + getDescription()+ "," +
            "cookingTime" + ":" + getCookingTime()+ "," +
            "image" + ":" + getImage()+ "]" +
            "  "
     + outputString;
  }
}